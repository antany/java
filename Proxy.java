import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TrafficMonitorProxy {

    private final int proxyPort;

    public TrafficMonitorProxy(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void start() throws IOException {
        Selector selector = Selector.open();

        // Open proxy server socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(proxyPort));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Proxy server started on port: " + proxyPort);

        while (true) {
            selector.select();
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();
                selectedKeys.remove();

                if (key.isAcceptable()) {
                    handleAccept(selector, serverSocketChannel);
                } else if (key.isReadable()) {
                    handleRead(key);
                }
            }
        }
    }

    private void handleAccept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel clientSocket = serverSocketChannel.accept();
        clientSocket.configureBlocking(false);
        clientSocket.register(selector, SelectionKey.OP_READ);

        // Connect to the target server
        SocketChannel targetSocket = SocketChannel.open();
        targetSocket.configureBlocking(false);
        targetSocket.connect(new InetSocketAddress("localhost", 8080)); // Replace with actual target server address and port
        targetSocket.register(selector, SelectionKey.OP_READ, clientSocket);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel socket = (SocketChannel) key.channel();
        SocketChannel targetSocket = (SocketChannel) key.attachment();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = socket.read(buffer);

        if (bytesRead > 0) {
            buffer.flip();
            String message = new String(buffer.array(), 0, bytesRead, StandardCharsets.UTF_8);

            // Forward message to target server
            targetSocket.write(buffer);

            // Print captured traffic
            System.out.println("[" + socket.getRemoteAddress() + " -> " + targetSocket.getRemoteAddress() + "] " + message);
        }

        // Handle empty reads and socket closure appropriately
    }

    public static void main(String[] args) throws IOException {
        int proxyPort = 8081; // Choose a suitable port for your proxy
        new TrafficMonitorProxy(proxyPort).start();
    }
              }
      
