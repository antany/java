package in.antany.csseditor.listerners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class CSSEditorKeyListener extends KeyAdapter {

	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		CommonActions.RefreshHTMLPane(e);;
	}

}
