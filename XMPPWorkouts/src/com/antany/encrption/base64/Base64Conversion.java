package com.antany.encrption.base64;

import java.util.HashMap;

/**
 * @author antany Its used for base64 encoding and decoding
 * 
 *         Implemented based on RFC 4648
 * 
 */
public class Base64Conversion {

	private static final char[] base64EncodeString = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };

	private static HashMap<String, Integer> decodeHashMap = null;

	/**
	 * This method is used to convert the normal text to base64 encoded text
	 * format
	 * 
	 * Thanks to http://www.wikihow.com/Encode-a-String-to-Base64-With-Java
	 * Thanks to http://en.wikipedia.org/wiki/Base64#RFC_4648
	 * 
	 * @param srcString
	 * @return base64 String
	 */
	public static String convertTextToBase64(String srcString) {

		byte[] srcStringBytes = srcString.getBytes();

		// this part of code is used to find the no of padding bits required for
		// Base64 conversion.
		int srcStringLength = srcStringBytes.length;

		int padBits = 3 - (srcStringLength % 3);
		padBits = padBits == 3 ? 0 : padBits;

		String base64String = "";

		for (int i = 0; i < srcStringLength; i += 3) {
			int temp3bytes = (srcStringBytes[i + 0] << 16)
					+ (((i + 1) >= srcStringLength ? 0 : srcStringBytes[i + 1]) << 8)
					+ (((i + 2) >= srcStringLength ? 0 : srcStringBytes[i + 2]));

			for (int j = 18; j >= 0; j -= 6) {
				int indexChar = (temp3bytes >> j) & 63;
				base64String = base64String + base64EncodeString[indexChar];
			}

		}

		base64String = base64String.substring(0, base64String.length()
				- padBits);
		base64String = base64String + "==".substring(0, padBits);

		return base64String;

	}

	/**
	 * This method is used to convert the base64 text to human readable text
	 * 
	 * @param base64String
	 * @return
	 */
	public static String covertBase64toText(String base64String) {

		initialize();

		int padBits = base64String.indexOf('=') > 0 ? base64String.length()
				- base64String.indexOf('=') : 0;
		if (padBits > 0) {
			base64String = base64String.substring(0, base64String.indexOf('='));
		}

		String humanReadableString = "";

		byte[] srcStringBytes = base64String.getBytes();

		for (int i = 0; i < srcStringBytes.length; i += 4) {

			String strTemp = String.valueOf(((char) srcStringBytes[i]));
			int tempInt = decodeHashMap.get(strTemp).intValue();

			strTemp = String.valueOf(((char) srcStringBytes[i + 1]));
			tempInt = tempInt << 6 | decodeHashMap.get(strTemp).intValue();

			if ((i + 2) < srcStringBytes.length) {
				strTemp = String.valueOf(((char) srcStringBytes[i + 2]));
				tempInt = tempInt << 6 | decodeHashMap.get(strTemp).intValue();
			} else {
				tempInt = tempInt << 6 | 0;
			}

			if ((i + 3) < srcStringBytes.length) {
				strTemp = String.valueOf(((char) srcStringBytes[i + 3]));
				tempInt = tempInt << 6 | decodeHashMap.get(strTemp).intValue();
			} else {
				tempInt = tempInt << 6 | 0;
			}

			for (int j = 16; j >= 0; j -= 8) {
				int indexChar = (tempInt >> j) & 255;
				humanReadableString = humanReadableString + (char) indexChar;
			}

		}

		humanReadableString = humanReadableString.trim();

		return humanReadableString;
	}

	/**
	 * Used to load the hashmap only once for the decoding purpose;
	 */
	private static void initialize() {

		if (decodeHashMap == null) {

			decodeHashMap = new HashMap<String, Integer>();
			decodeHashMap.put("A", 0);
			decodeHashMap.put("B", 1);
			decodeHashMap.put("C", 2);
			decodeHashMap.put("D", 3);
			decodeHashMap.put("E", 4);
			decodeHashMap.put("F", 5);
			decodeHashMap.put("G", 6);
			decodeHashMap.put("H", 7);
			decodeHashMap.put("I", 8);
			decodeHashMap.put("J", 9);
			decodeHashMap.put("K", 10);
			decodeHashMap.put("L", 11);
			decodeHashMap.put("M", 12);
			decodeHashMap.put("N", 13);
			decodeHashMap.put("O", 14);
			decodeHashMap.put("P", 15);
			decodeHashMap.put("Q", 16);
			decodeHashMap.put("R", 17);
			decodeHashMap.put("S", 18);
			decodeHashMap.put("T", 19);
			decodeHashMap.put("U", 20);
			decodeHashMap.put("V", 21);
			decodeHashMap.put("W", 22);
			decodeHashMap.put("X", 23);
			decodeHashMap.put("Y", 24);
			decodeHashMap.put("Z", 25);
			decodeHashMap.put("a", 26);
			decodeHashMap.put("b", 27);
			decodeHashMap.put("c", 28);
			decodeHashMap.put("d", 29);
			decodeHashMap.put("e", 30);
			decodeHashMap.put("f", 31);
			decodeHashMap.put("g", 32);
			decodeHashMap.put("h", 33);
			decodeHashMap.put("i", 34);
			decodeHashMap.put("j", 35);
			decodeHashMap.put("k", 36);
			decodeHashMap.put("l", 37);
			decodeHashMap.put("m", 38);
			decodeHashMap.put("n", 39);
			decodeHashMap.put("o", 40);
			decodeHashMap.put("p", 41);
			decodeHashMap.put("q", 42);
			decodeHashMap.put("r", 43);
			decodeHashMap.put("s", 44);
			decodeHashMap.put("t", 45);
			decodeHashMap.put("u", 46);
			decodeHashMap.put("v", 47);
			decodeHashMap.put("w", 48);
			decodeHashMap.put("x", 49);
			decodeHashMap.put("y", 50);
			decodeHashMap.put("z", 51);
			decodeHashMap.put("0", 52);
			decodeHashMap.put("1", 53);
			decodeHashMap.put("2", 54);
			decodeHashMap.put("3", 55);
			decodeHashMap.put("4", 56);
			decodeHashMap.put("5", 57);
			decodeHashMap.put("6", 58);
			decodeHashMap.put("7", 59);
			decodeHashMap.put("8", 60);
			decodeHashMap.put("9", 61);
			decodeHashMap.put("+", 62);
			decodeHashMap.put("/", 63);

		}
	}

	public static void main(String[] args) {
		// Test purpose
		// Thanks for http://en.wikipedia.org/wiki/Base64
		String actualString = "antany";
		System.out.println(convertTextToBase64(actualString));
		System.out.println(covertBase64toText(convertTextToBase64(actualString)));
	}

	/*
	 * 
	 * for(int j=1;j<=8;j++){ byte b1 = (byte)(b & 1); System.out.println(b1); b
	 * = (byte)(b >> 1); //System.out.println(b); }
	 */

}
