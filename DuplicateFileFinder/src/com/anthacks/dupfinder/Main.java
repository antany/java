package com.anthacks.dupfinder;

import java.io.File;
import java.util.ArrayList;

import com.anthacks.dupfinder.vo.FileInfo;

public class Main {

	public static String FILE_START_PATH = "/media/antany/01CD9D0E7F9274E0/Movies";

	public static ArrayList<FileInfo> fileInfoList = new ArrayList<FileInfo>();

	public static void main(String[] args) throws Exception {
		iterateFiles(new File(FILE_START_PATH));
		System.out.println(fileInfoList.size());
	}

	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					iterateFiles(file);
				} else {
					FileInfo fileInfo = new FileInfo();
					fileInfo.setFileSize(file.length());
					fileInfo.setFilePath(file.getAbsolutePath());
					fileInfo.setFullFileName(file.getName());
					
					System.out.println(file.getAbsolutePath());
					System.out.println(fileInfo.getFileName());
					fileInfoList.add(fileInfo);
				}
			}
		}
	}

}
