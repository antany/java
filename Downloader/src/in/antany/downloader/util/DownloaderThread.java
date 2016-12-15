package in.antany.downloader.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class DownloaderThread implements Runnable {
	private URL downloadURL;
	private FileChannel targetFileChannel;
	private long filePosition = 0;
	private long startByteRange = 0;
	private long endByteRange = 0;
	private int byteCount = 4096;
	private int noOfBytesTransferred = 0;
	
	

	public DownloaderThread(URL downloadURL, FileChannel targetFileChannel)
			throws Exception {
		this.downloadURL = downloadURL;
		this.targetFileChannel = targetFileChannel;
	}

	private void init() throws Exception {
		if (endByteRange == 0) {
			endByteRange = DownloaderUtils.getContentLength(downloadURL);
		}
	}

	public long getFilePosition() {
		return filePosition;
	}

	public void setFilePosition(long filePosition) {
		this.filePosition = filePosition;
	}

	public long getStartByteRange() {
		return startByteRange;
	}

	public void setStartByteRange(long startByteRange) {
		this.startByteRange = startByteRange;
	}

	public long getEndByteRange() {
		return endByteRange;
	}

	public void setEndByteRange(long endByteRange) {
		this.endByteRange = endByteRange;
	}

	public int getByteCount() {
		return byteCount;
	}

	public void setByteCount(int byteCount) {
		this.byteCount = byteCount;
	}
	
	public int getNoOfBytesTransferred(){
		return noOfBytesTransferred;
	}
	
	
	@Override
	public void run() {
		try {
			init();
			System.out.println("-----"+new Date());
			HttpURLConnection connection = (HttpURLConnection) downloadURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Range", "bytes=" + startByteRange
					+ "-" + endByteRange);
			System.out.println("bytes=" + startByteRange
					+ "-" + endByteRange);
			InputStream is = connection.getInputStream();
			byte[] byteArray = new byte[byteCount];
			ByteBuffer byteBuffer = ByteBuffer.allocate(byteCount);
			int n;
			while ((n = is.read(byteArray)) != -1) {
				byteBuffer.put(byteArray, 0, n);
				byteBuffer.flip();
				targetFileChannel.write(byteBuffer, filePosition);
				filePosition = filePosition + n;
				noOfBytesTransferred = noOfBytesTransferred+n;
				byteBuffer.position(0);
				byteBuffer.limit(byteCount);
			}
			System.out.println("++++++"+new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}