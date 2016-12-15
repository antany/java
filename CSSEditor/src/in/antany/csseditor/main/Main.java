package in.antany.csseditor.main;

import in.antany.csseditor.ui.CSSEditorFrame;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CSSEditorFrame();
			}
		});
	}
}
