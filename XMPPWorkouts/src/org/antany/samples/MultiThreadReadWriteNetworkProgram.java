package org.antany.samples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadReadWriteNetworkProgram {

	public static Socket clientSocket = null;

	public static void main(String[] args) throws Exception {

		clientSocket = new Socket("talk.google.com", 5222);
		Thread tSender = new Thread(new ThreadSender(clientSocket));
		Thread tReceiver = new Thread(new ThreadReceiver(clientSocket));
		tSender.start();
		tReceiver.start();
	}
}

class ThreadSender implements Runnable {

	Socket clientSocket;
	BufferedReader buffReader = new BufferedReader(new InputStreamReader(
			System.in));
	public static PrintWriter out = null;

	public ThreadSender(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			while (true) {
				String str = buffReader.readLine();
				//str = "<stream:stream xmlns='jabber:client' xmlns:stream='http://etherx.jabber.org/streams' xmlns:sasl='http://www.iana.org/assignments/sasl-mechanisms' to='jabber.org'>";
				out.println(str);

				if (str.equalsIgnoreCase("exit")) {
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ThreadReceiver implements Runnable {

	Socket clientSocket;
	public BufferedReader in = null;

	public ThreadReceiver(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			while (true) {
				String inputString = in.readLine();
				if(inputString!=null){
					System.out.println(inputString);
				}	
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}

class UnThreaded{
		
}