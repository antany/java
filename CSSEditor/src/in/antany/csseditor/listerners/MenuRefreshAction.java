package in.antany.csseditor.listerners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRefreshAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		CommonActions.RefreshHTMLPane(e);
	}
}
