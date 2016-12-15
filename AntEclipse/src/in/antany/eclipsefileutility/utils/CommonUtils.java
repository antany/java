package in.antany.eclipsefileutility.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

public class CommonUtils {

	public static void copyStringToClipBoard(String content) {
	
			Clipboard clipBoard = new Clipboard(Display.getCurrent());
			TextTransfer textTransfer = TextTransfer.getInstance();
			Object[] textArray = new Object[] { content };
			Transfer[] transfers = new Transfer[] { textTransfer };
			clipBoard.setContents(textArray, transfers);
			clipBoard.dispose();
		}
	
		public static String getQualifiedClassName(String path) {
	
			String qualifiedName = "";
	
			path = path.replaceAll("//.*", "");
			path = path.replaceAll("(?s)/\\*.*?\\*/", "");
	
			Pattern p = Pattern.compile("package\\s+([a-z0-9A-z.]+?);");
			Matcher m = p.matcher(path);
			if (m.find()) {
	
				qualifiedName = m.group(1);
			}
	
			return qualifiedName;
		}

	

}
