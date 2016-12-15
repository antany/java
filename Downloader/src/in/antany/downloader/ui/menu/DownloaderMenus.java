package in.antany.downloader.ui.menu;

import in.antany.common.utils.Resources;
import in.antany.downloader.ui.actions.MenuOpenAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DownloaderMenus extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DownloaderMenus() {
		JMenu menuFile = new JMenu(Resources.getValue("menu.file"));
		JMenuItem menuItemOpen = new JMenuItem(Resources.getValue("menu.open"));
		menuItemOpen.addActionListener(new MenuOpenAction());
		menuFile.add(menuItemOpen);
		add(menuFile);
	}

}
