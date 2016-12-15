package in.antany.downloader.util;

import in.antany.common.system.JavaObjectsPool;
import in.antany.downloader.ui.DownloaderTableModel;
import in.antany.downloader.ui.component.DLProgressBar;
import in.antany.downloader.util.dto.DownloadDto;

import java.net.URL;
import java.util.Date;

public class MakeDownloadDecisionAndTriggerDownload {

	private URL url;
	private String targetFile;

	public MakeDownloadDecisionAndTriggerDownload(URL url, String targetFile) {
		this.url = url;
		this.targetFile = targetFile;
	}

	public void beginDownload() {
		try {
			long size = DownloaderUtils.getContentLength(url);
			boolean multiSupport = DownloaderUtils
					.isMultiDownloadSupported(url);
			DownloaderTableModel dwm = (DownloaderTableModel) JavaObjectsPool
					.getObject(DownloaderTableModel.class);
			int currRowCount = dwm.getRowCount();
			currRowCount++;
			DownloadDto dlDto = new DownloadDto();
			dlDto.setNo(currRowCount);
			dlDto.setMultiDownloadSupport(multiSupport);

			dlDto.setUrl(url.toString());
			dlDto.setStatus("Started..");
			DLProgressBar progressBar = new DLProgressBar(dwm);
			progressBar.setRowNumber(currRowCount);
			dlDto.setProgressBar(progressBar);

			double sizeInMB = (((double) size) / 1024d) / 1024d;
			int noOfParallelDownloads = 1;
			String sizeString;
			if (sizeInMB < 1) {
				double sizeInKB = sizeInMB * 1024d;
				sizeString = (Math.round(sizeInKB) + " KB");
			} else {
				sizeString = (Math.round(sizeInMB) + " MB");
				if (sizeInMB < 5) {
					//noOfParallelDownloads = (int) sizeInMB;
				} else {
					//noOfParallelDownloads = 5;
				}
			}
			dlDto.setSize(sizeString);
			dwm.addRow(dlDto);
			System.out.println(targetFile);
			Download dw = new Download(url.toString(), targetFile);
			dw.setNoOfParallelDownloads(noOfParallelDownloads);
			dw.setProgressBar(progressBar);
			new Thread(dw).start();
			System.out.println(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
