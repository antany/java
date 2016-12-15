package com.anthacks.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HexReader {
	public static void main(String[] args) throws Exception {
		File inputFile = new File(
				"/media/antany/49BAC6B862511784/Best of Ilayaraja/Ye Paadal - Priya.mp3");
		FileInputStream fis = new FileInputStream(inputFile);
		DataInputStream dis = new DataInputStream(fis);
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(
				"/media/antany/49BAC6B862511784/Best of Ilayaraja/Ye Paadal - Priya.mp3.hex"));

		int ch;
		int address = 0x00;
		String rowString = "";
		dos.writeBytes("Start Address\t00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F \tActual\n");
		dos.writeBytes("---------------------------------------------------------------------------------\n");
		while ((ch = dis.read()) != -1) {
			if (address == 0 || address % 16 == 0) {
				dos.writeBytes(getFormattedHexAddress(address) + "\t");

			}
			address = address + 1;
			dos.writeBytes(("00" + Integer.toHexString(ch)).substring(Integer
					.toHexString(ch).length()) + " ");
			if (ch != 0)
				rowString = rowString + "" + (char) ch;

			if (address % 16 == 0) {
				dos.writeBytes("\t"
						+ (rowString).replace((char) 0x0D, ' ').replace(
								(char) 0x0A, ' ') + "\n");
				rowString = "";
			}
		}

		dos.flush();
		dos.close();
		dis.close();
	}

	public static String getFormattedHexAddress(int i) {
		String str = Integer.toHexString(i);
		int preCount = 10-str.length();
		
				
		for(int j=0;j<preCount;j++){
			str ="0"+str;  
		}
		return "0x"+str+"h";
	}
}
