package com.anthacks.dupfinder.vo;

import java.util.Date;

public class FileInfo {
	private String fileName;
	private String filePath;
	private String fileType;
	private long fileSize;
	private Date creationDate;
	private Date lastModifiedDate;

	public String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	private void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setFullFileName(String fullFileName) {
		String fileName = fullFileName.replaceAll("\\..*", "");
		String fileType = fullFileName.replaceAll(".*\\.", "");
		
		fileName = fileName.replaceAll("\\[.*?\\]", "");
		fileName = fileName.replaceAll("\\(.*?\\)", "");
		fileName = fileName.replaceAll("[^a-zA-Z0-9 ]", "");
		fileName = fileName.replaceAll("(?i)DVDRip", "");
		fileName = fileName.replaceAll("(?i)TMT", "");
		fileName = fileName.replaceAll("(?i)Team", "");
		fileName = fileName.replaceAll("(?i)MJY", "");
		fileName = fileName.replaceAll("(?i)TMR", "");
		fileName = fileName.replaceAll("(?i)Ayn", "");
		fileName = fileName.replaceAll("(?i)Xvid", "");
		fileName = fileName.replaceAll("(?i)\\dCD", "");
		setFileName(fileName);
		setFileType(fileType);
	}

	public String getFullFileName() {
		return getFileName() + "." + getFileType();
	}
	
	public String toString(){
		return getFullFileName();
	}
}
