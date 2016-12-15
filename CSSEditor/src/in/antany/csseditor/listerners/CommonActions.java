package in.antany.csseditor.listerners;

import in.antany.csseditor.html.HTMLGenerator;
import in.antany.csseditor.system.JavaObjectsPool;
import in.antany.csseditor.ui.CSSEditorPane;
import in.antany.csseditor.ui.HTMLViewPane;

public class CommonActions {
	static public void  RefreshHTMLPane(Object e){
		CSSEditorPane cssTextPane = (CSSEditorPane) JavaObjectsPool
				.getObject(CSSEditorPane.class);
		HTMLViewPane htmlViewPane = (HTMLViewPane) JavaObjectsPool
				.getObject(HTMLViewPane.class);

		htmlViewPane.refreshWebEngine(HTMLGenerator
				.getGeneratedHTMLCode(cssTextPane.getText()));
	}
}
