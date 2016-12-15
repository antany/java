package org.antany.samples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class ConnectGtalkServerUsingURL {
	public static void main(String[] args) throws Exception {

		URL gtalkURL = new URL("http://talk.google.com:443");
		URLConnection gtalkConnection = gtalkURL.openConnection();
		gtalkConnection.setDoInput(true);
		gtalkConnection.setDoOutput(true);
		String strInitiation = "<?xml version=\"1.0\"?>\n"
				+ "    <stream:stream\n" + "        to=\"gmail.com\"\n"
				+ "        xmlns=\"jabber:client\"\n"
				+ "        xmlns:stream=\"http://etherx.jabber.org/streams\"\n"
				+ "        version=\"1.0\">";
		OutputStreamWriter out = new OutputStreamWriter(gtalkConnection
				.getOutputStream());
		out.write(strInitiation);
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(gtalkConnection
				.getInputStream()));

		String decodedString;

		while ((decodedString = in.readLine()) != null) {
			System.out.println(decodedString);
		}
		in.close();

	}
}
