package in.antany.csseditor.listerners;

import in.antany.csseditor.css.CSSParser;
import in.antany.csseditor.system.JavaObjectsPool;
import in.antany.csseditor.ui.CSSEditorPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuOpenAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		CSSEditorPane cssTextPane = (CSSEditorPane) JavaObjectsPool
				.getObject(CSSEditorPane.class);
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"CSS Files", "css");
		jfc.setFileFilter(filter);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String css = CSSParser.readContentFromFile(jfc.getSelectedFile().getAbsolutePath());
			cssTextPane.setText(css);
		}
		CommonActions.RefreshHTMLPane(e);
	}
}
