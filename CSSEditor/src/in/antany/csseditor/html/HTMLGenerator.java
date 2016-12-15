package in.antany.csseditor.html;

import in.antany.csseditor.css.CSSParser;
import in.antany.csseditor.templates.HTMLTemplates;

import java.util.ArrayList;

public class HTMLGenerator {
	public static String getGeneratedHTMLCode(String cssSrc) {
		String string = new String(HTMLTemplates.HTML_BODY);
		ArrayList<String> arrayList = (ArrayList<String>)CSSParser.getCSSNames(cssSrc);
		StringBuilder stringContent = new StringBuilder();
		for(String cssName : arrayList){
			stringContent.append(HTMLTemplates.TR_TD.replaceAll("\\{style_name\\}", cssName));
		}
		string = string.replaceAll("\\{style_info\\}", cssSrc);
		string = string.replaceAll("\\{table_body_info\\}", stringContent.toString());
		return string;
	}
}
