package in.antany.csseditor.ui;

import in.antany.csseditor.properties.CSSEditorProperties;
import in.antany.csseditor.system.JavaObjectsPool;

import java.awt.Dimension;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JPanel;

public class HTMLViewPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;
	
	int editorWidth;int editorHeight;

	public HTMLViewPane() {
		add(jfxPanel);
		editorWidth = CSSEditorProperties.getEditorWidth();
		editorHeight = CSSEditorProperties.getEditorHeight();

		editorHeight = (editorHeight * 15) / 100;
		editorWidth = editorWidth - 20;
		
		setPreferredSize(new Dimension(editorWidth,editorHeight));
		createScene();
		JavaObjectsPool.putObject(this);
	}

	private void createScene() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				WebView view = new WebView();
				view.setMinSize(editorWidth-5, editorHeight-5);
				view.setMaxSize(editorWidth-5, editorHeight-5);
				engine = view.getEngine();
				jfxPanel.setScene(new Scene(view));
				//engine.load("file:///c:/vas.html");
			}
		});
	}

	public void refreshWebEngine(final String html) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				engine.loadContent(html);
			}
		});
	}

}
