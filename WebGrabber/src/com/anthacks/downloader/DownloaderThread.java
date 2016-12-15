package com.anthacks.downloader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Semaphore;

public class DownloaderThread extends Thread {
	String fileURL;
	String destinationPath;
	Semaphore s1;

	public DownloaderThread(String fileURL, String destinationPath, Semaphore s1) {
		this.fileURL = fileURL;
		this.destinationPath = destinationPath;
		this.s1 = s1;
	}

	@Override
	public void run() {

		DataOutputStream os = null;
		DataInputStream is = null;
		try {
			s1.acquire();
			String protocol = fileURL.replaceAll(":.*", "");
			String baseURL = fileURL.replaceAll("http://([a-zA-Z.0-9]*?)/.*",
					"$1");
			String urlPath = fileURL.replaceAll("http://[a-zA-Z.0-9]*?(/.*)",
					"$1");

			URI uri = new URI(protocol, baseURL, urlPath, null);
			URL url = uri.toURL();

			HttpURLConnection fileURLConnection = (HttpURLConnection) url
					.openConnection();

			System.out.println("Started Downloading " + fileURL + " to "
					+ destinationPath);

			os = new DataOutputStream(new FileOutputStream(new File(
					destinationPath)));


			is = new DataInputStream(fileURLConnection.getInputStream());

			byte[] read = new byte[4096];

			int n;
			while ((n = is.read(read)) != -1) {
				os.write(read, 0, n);
			}

			os.flush();
			is.close();
			os.close();
			s1.release();
			System.out.println("\nDownload Sucessful");
		} catch (Exception e) {
			s1.release();
			System.err.println("\nDownload failed.." + fileURL);
		}
	}

}