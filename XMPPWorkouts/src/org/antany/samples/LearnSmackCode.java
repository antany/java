package org.antany.samples;

import java.io.File;

public class LearnSmackCode {

	public static void main(String[] args) {
		  String javaHome = System.getProperty("java.home");
	        StringBuilder buffer = new StringBuilder();
	        buffer.append(javaHome).append(File.separator).append("lib");
	        buffer.append(File.separator).append("security");
	        buffer.append(File.separator).append("cacerts");
	        String truststorePath = buffer.toString();
	        
	        System.out.println(truststorePath);
	}
}
