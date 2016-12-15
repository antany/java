package com.anthacks.webgrabber.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.anthacks.util.CommonUtils;

public class TestMain {
	public static void main(String[] args) throws Exception{
		String testURL = "http://tamilasia.com/music/audio/Tamil Music Albums/Ilayaraja Hits/Aagaya Gangai - Dharma Yudham.mp3";
		downloadSpecficByte(testURL, null, 1, 128);
	}
	
	public static void downloadSpecficByte(String fileURL, String destinationPath, int startByte, int byteSize)
			throws Exception {
		DataOutputStream os = null;

		DataInputStream is = null;

		try {
			String protocol = fileURL.replaceAll(":.*", "");
			String baseURL = fileURL.replaceAll("http://([a-zA-Z.0-9]*?)/.*",
					"$1");
			String urlPath = fileURL.replaceAll("http://[a-zA-Z.0-9]*?(/.*)",
					"$1");

			URI uri = new URI(protocol, baseURL, urlPath, null);
			URL url = uri.toURL();

			HttpURLConnection fileURLConnection = (HttpURLConnection) url
					.openConnection();

			fileURLConnection.setRequestProperty("Range", "bytes="+"0-10");
			
			System.out.println("Started Downloading " + fileURL + " to "
					+ destinationPath);

			/*os = new DataOutputStream(new FileOutputStream(new File(
					destinationPath)));*/

			int fileLength = fileURLConnection.getContentLength();

			is = new DataInputStream(fileURLConnection.getInputStream());

			byte[] read = new byte[4096];

			int n;
			int progress = 0;

			while ((n = is.read(read)) != -1) {
				
				for(int i=0;i<=n;i++){
					System.out.println(i+"\t"+read[i]+"\t"+Integer.toHexString(read[i])+"\t"+(char)read[i]);
				}
				//os.write(read, 0, n);
				progress = progress + n;
			}

			System.out.println("\nDownload Sucessful");
		} catch (Exception e) {
			System.err.println("\nDownload failed.." + fileURL);
			e.printStackTrace();
		} finally {
			//os.flush();
			is.close();
			//os.close();
		}

	}
	
}
