package org.antany.samples;

import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) throws IOException {

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket("talk.google.com", 5222);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: taranis.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: taranis.");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput = "<?xml version='1.0'?>\n" + 
				"   <stream:stream\n" + 
				"       to='gmail.com'\n" + 
				"       xmlns='jabber:client'\n" + 
				"       xmlns:stream='http://etherx.jabber.org/streams'\n" + 
				"       version='1.0'>";

			System.out.println("1");
			out.println(userInput);
			System.out.println("2");
			System.out.println("echo: " + in.readLine());
			System.out.println("3");
			out.println("<starttls xmlns='urn:ietf:params:xml:ns:xmpp-tls'/>");
			System.out.println("4");
			System.out.println("echo: " + in.readLine());
			System.out.println("5");
		

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}
}
