package in.antany.csseditor.css;

import in.antany.csseditor.exception.CSSFileNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSSParser {
	public static String readContentFromFile(String fileName){
		char[] charContent;
		try{
			File srcFile = new File(fileName);
			charContent = new char[(int) srcFile.length()];
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					srcFile));
			bufferedReader.read(charContent);
			bufferedReader.close();
		}catch(Exception e){
			throw new CSSFileNotFoundException();
		}
		return String.copyValueOf(charContent);
	}
	
	public static List<String> getCSSNames(String cssSrc){
		ArrayList<String> cssName = new ArrayList<>();
		Pattern p = Pattern.compile(CSS_RULE_PATTERN);
		Matcher m = p.matcher(cssSrc);
		while(m.find()){
			cssName.add(m.group(1).trim().replaceFirst("\\.", ""));
		}
		return cssName;
	}
	
	
	private static final String CSS_RULE_PATTERN = "(?s)(.*?)\\{.*?\\}";
}
