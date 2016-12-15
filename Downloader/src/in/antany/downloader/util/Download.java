package in.antany.downloader.util;

import in.antany.downloader.ui.component.DLProgressBar;

import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Download implements Runnable {
	private String downloadURL;
	private String targetFilePath;
	private int noOfParallelDownloads = 1;
	private URL downloadLink = null;
	private FileChannel targetFileChannel = null;
	private RandomAccessFile ram;
	private int byteCount = 4096;
	private DownloaderThread[] downloadThreadArray;
	private long totalDownloadByte = 0;
	private DLProgressBar progressBar;

	public int getNoOfParallelDownloads() {
		return noOfParallelDownloads;
	}

	public void setNoOfParallelDownloads(int noOfParallelDownloads) {
		this.noOfParallelDownloads = noOfParallelDownloads;
	}

	public int getByteCount() {
		return byteCount;
	}

	public void setByteCount(int byteCount) {
		this.byteCount = byteCount;
	}

	public void setProgressBar(DLProgressBar progressBar){
		this.progressBar = progressBar;
	}
	
	public DLProgressBar getProgressBar(){
		return progressBar;
	}
	
	
	public Download(String downloadURL, String targetFilePath) throws Exception {
		this.downloadURL = downloadURL;
		this.targetFilePath = targetFilePath;
	}

	public void run() {
		try {
			init();
			System.out.println(noOfParallelDownloads);
			final long fileSize = DownloaderUtils.getContentLength(downloadLink);
			long fileChunk = fileSize / noOfParallelDownloads;
			long remByte = fileSize % noOfParallelDownloads;
			ExecutorService es = Executors.newCachedThreadPool();
			long startTime = System.currentTimeMillis();
			new Thread(new Runnable() {
				@Override
				public void run() {
					int totByte;
					while (totalDownloadByte!=fileSize) {
						try {
							Thread.sleep(1000);	
							totByte = 0;
							for(int i=0;i<noOfParallelDownloads;i++){
								totByte = totByte + downloadThreadArray[i].getNoOfBytesTransferred();
							}
							totalDownloadByte = totByte;
							progressBar.setDLProgress((int)((totalDownloadByte*100)/fileSize));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			for (int i = 0; i < noOfParallelDownloads; i++) {
				
				long byteStart = (long)i * fileChunk;
				long byteEnd = ((i + 1) * fileChunk) - 1;
				if ((i + 1) == noOfParallelDownloads) {
					byteEnd = byteEnd + remByte;
				}
				DownloaderThread dt = new DownloaderThread(downloadLink,
						targetFileChannel);
				dt.setFilePosition(byteStart);
				dt.setStartByteRange(byteStart);
				dt.setEndByteRange(byteEnd);
				dt.setByteCount(byteCount);
				downloadThreadArray[i] = dt;
			
				es.execute(dt);
			}
			System.out.println(">>>>>>>>>>"+new Date());
			es.shutdown();
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			close();
			long endTime = System.currentTimeMillis();
			System.out.println("Time to download " + (endTime - startTime)
					/ 1000 + " Seconds ");
			
		} catch (Exception e) {
			System.out.println("Error while downloading " + downloadURL);
		}
	}

	private void init() throws Exception {
		downloadLink = new URL(downloadURL);
		ram = new RandomAccessFile(targetFilePath, "rw");
		targetFileChannel = ram.getChannel();
		downloadThreadArray = new DownloaderThread[noOfParallelDownloads];
	}

	private void close() throws Exception {
		ram.close();
		targetFileChannel.close();
	}

}