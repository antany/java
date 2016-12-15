package in.antany.csseditor.templates;

public class HTMLTemplates {
	public static final String HTML_BODY ="<HTML>\r\n" + 
			"	<HEAD>\r\n" + 
			"		<STYLE TYPE=\"text/css\">\r\n" + 
			"			{style_info}\r\n" + 
			"		</STYLE>\r\n" + 
			"	<BODY>\r\n" + 
			"		<TABLE border=1>\r\n" + 
			"			{table_body_info}\r\n" + 
			"		</TABLE>\r\n" + 
			"	</BODY>\r\n" + 
			"<HTML>";
	
	public static final String TR_TD="<TR>\r\n" + 
			"	<TD class=\"{style_name}\">\r\n" + 
			"		{style_name}\r\n" + 
			"	</TD>\r\n" + 
			"</TR>";
}
