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



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficMonitorProxy {

    private final int proxyPort;
    private final ExecutorService executorService;

    public TrafficMonitorProxy(int proxyPort) {
        this.proxyPort = proxyPort;
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
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
                    executorService.submit(() -> handleAccept(selector, serverSocketChannel));
                }
            }
        }
    }

    private void handleAccept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel clientSocket = serverSocketChannel.accept();
        clientSocket.configureBlocking(false);

        // Connect to the target server in a separate thread
        executorService.submit(() -> connectToTargetServer(clientSocket, selector));
    }

    private void connectToTargetServer(SocketChannel clientSocket, Selector selector) {
        try {
            SocketChannel targetSocket = SocketChannel.open();
            targetSocket.configureBlocking(false);
            targetSocket.connect(new InetSocketAddress("localhost", 8080)); // Replace with actual target server address and port
            targetSocket.register(selector, SelectionKey.OP_READ, clientSocket);

            handleReadWrite(clientSocket, targetSocket, selector);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleReadWrite(SocketChannel clientSocket, SocketChannel targetSocket, Selector selector) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            // Read from client
            int bytesRead = clientSocket.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                String message = new String(buffer.array(), 0, bytesRead, StandardCharsets.UTF_8);

                // Forward to target server
                targetSocket.write(buffer);

                // Print captured traffic
                System.out.println("[" + clientSocket.getRemoteAddress() + " -> " + targetSocket.getRemoteAddress() + "] " + message);

                buffer.clear();
            }

            // Read from target server (similar logic)

            // Handle empty reads, disconnections, and potential errors
        }
    }

    public static void main(String[] args) throws IOException {
        int proxyPort = 8081; // Choose a suitable port for your proxy
        new TrafficMonitorProxy(proxyPort).start();
    }
    }
        
