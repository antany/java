package in.antany.csseditor.ui;

import in.antany.csseditor.listerners.CSSEditorKeyListener;
import in.antany.csseditor.properties.CSSEditorProperties;
import in.antany.csseditor.system.JavaObjectsPool;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class CSSEditorPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	private JTextPane cssEditorPage = null;

	public CSSEditorPane() {
		super(new JTextPane());

		int editorWidth = CSSEditorProperties.getEditorWidth();
		int editorHeight = CSSEditorProperties.getEditorHeight();

		editorHeight = (editorHeight * 75) / 100;
		editorWidth = editorWidth - 20;

		cssEditorPage = (JTextPane) getViewport().getView();

		cssEditorPage
				.setPreferredSize(new Dimension(editorWidth, editorHeight));
		
		cssEditorPage.addKeyListener(new CSSEditorKeyListener());
		
		validate();
		JavaObjectsPool.putObject(this);
	}

	public void setText(String str) {
		cssEditorPage.setText(str);
	}

	public String getText() {
		return cssEditorPage.getText();
	}

	public JTextPane getTextPane() {
		return cssEditorPage;
	}
}
