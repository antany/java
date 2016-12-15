package in.antany.downloader.util.dto;

import in.antany.downloader.ui.component.DLProgressBar;

import java.io.Serializable;

import javax.swing.JProgressBar;

public class DownloadDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer no;
	private String url;
	private String size ;
	private Boolean multiDownloadSupport;
	private DLProgressBar progressBar;
	private String status;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public boolean isMultiDownloadSupport() {
		return multiDownloadSupport;
	}
	public void setMultiDownloadSupport(boolean multiDownloadSupport) {
		this.multiDownloadSupport = multiDownloadSupport;
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(DLProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
