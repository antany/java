package in.antany.downloader.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloaderUtils {
	public static int getContentLength(URL url) throws IOException {
		int contentLength;
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("HEAD");
		contentLength = connection.getContentLength();
		return contentLength;
	}

	public static boolean isValidURL(URL url) {
		boolean validURL = false;
		int responseCode;
		try {
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("HEAD");
			responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				validURL = true;
			}
		} catch (Exception e) {
			validURL = false;
		}
		return validURL;
	}

	public static boolean isMultiDownloadSupported(URL url) {
		boolean multiDownloadSupport = false;
		int responseCode;
		try {
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Range", "bytes=0-1");
			responseCode = connection.getResponseCode();
			if (responseCode == 206) {
				multiDownloadSupport = true;
			}
		} catch (Exception e) {
			multiDownloadSupport = false;
		}
		return multiDownloadSupport;
	}
}