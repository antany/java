package in.antany.csseditor.ui;

import in.antany.csseditor.listerners.MenuOpenAction;
import in.antany.csseditor.listerners.MenuRefreshAction;
import in.antany.csseditor.properties.CSSEditorResources;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CSSMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public CSSMenu() {
		JMenu menuFile = new JMenu(CSSEditorResources.getValue("menu.file"));
		JMenuItem menuItemOpen = new JMenuItem(CSSEditorResources.getValue("menu.open"));
		menuItemOpen.addActionListener(new MenuOpenAction());
		
		JMenuItem menuItemRefresh = new JMenuItem(CSSEditorResources.getValue("menu.refresh"));
		menuItemRefresh.addActionListener(new MenuRefreshAction());
		
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemRefresh);
		
		add(menuFile);
	}
}
