package in.antany.csseditor.ui;

import in.antany.csseditor.properties.CSSEditorResources;
import in.antany.csseditor.system.JavaObjectsPool;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CSSEditorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	static {
		setDefaultLookAndFeelDecorated(true);
	}
	public CSSEditorFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setTitle(CSSEditorResources.getValue("editor.title"));
		setJMenuBar(new CSSMenu());
		getContentPane().add(new CSSEditorPane());
		getContentPane().add(new HTMLViewPane());
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setVisible(true);
		JavaObjectsPool.putObject(this);
	}

}
