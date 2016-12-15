package in.antany.csseditor.properties;

import java.awt.Dimension;
import java.awt.Toolkit;

public class CSSEditorProperties {

	private static int editorWidth = 0;
	private static int editorHeight = 0;

	static {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		editorHeight = (int) dim.getHeight();
		editorWidth = (int) dim.getWidth();
	}

	public static int getEditorWidth() {
		return editorWidth;
	}

	public static int getEditorHeight() {
		return editorHeight;
	}
}
