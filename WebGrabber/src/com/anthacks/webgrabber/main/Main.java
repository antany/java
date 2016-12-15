package com.anthacks.webgrabber.main;

import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anthacks.downloader.DownloaderThread;
import com.anthacks.util.CommonUtils;

public class Main {

	public static int threadCount = 0;
	public static final int MAX_THREAD_COUNT = 10;
	
	public static void main(String[] args) throws Exception {
		String pageURL = "http://tamilasia.net/music-albums/ilayaraja-hits-all-time-best-melodies";

		String filePattern = "http://[^\">]*?\\.mp3";

		Pattern urlPatthen = Pattern.compile(filePattern);

		Matcher m = urlPatthen.matcher(CommonUtils.getPageContent(pageURL));
		
		Semaphore s1 = new Semaphore(MAX_THREAD_COUNT);

		String folderName = "D:/Best of Ilayaraja/";

		while (m.find()) {
			String url = m.group();
			String fileName = url.replaceAll("http://.*/", "");
				new DownloaderThread(url, folderName + fileName,s1).start();
		}

	}
}


