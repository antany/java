package in.antany.downloader.ui;

import in.antany.common.system.JavaObjectsPool;
import in.antany.common.utils.Resources;
import in.antany.downloader.ui.component.DLProgressBar;
import in.antany.downloader.ui.menu.DownloaderMenus;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	/** * */
	private static final long serialVersionUID = -3998176884877834048L;
	public static int screenHeight;
	public static int screenWidth;
	static {
		setDefaultLookAndFeelDecorated(true);
		screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
	}

	public MainFrame() {
		int windowHeight = 500;
		int windowWidth = 700;
		int leftMargin = (screenWidth - windowWidth) / 2;
		int topMargin = (screenHeight - windowHeight) / 2;
		setResizable(false);
		setJMenuBar(new DownloaderMenus());
		setTitle(Resources.getValue("project.title"));
		setLocation(leftMargin, topMargin);
		setSize(new Dimension(windowWidth, windowHeight));
		DownloaderTableModel dwm = new DownloaderTableModel();
		JTable statusTable = new JTable(dwm);
		JavaObjectsPool.putObject(dwm);
		statusTable.getColumnModel().getColumn(4).setCellRenderer(new DLProgressBar(dwm));
		statusTable.getColumnModel().getColumn(0).setPreferredWidth((windowWidth*5)/100);
		statusTable.getColumnModel().getColumn(1).setPreferredWidth((windowWidth*40)/100);
		statusTable.getColumnModel().getColumn(2).setPreferredWidth((windowWidth*10)/100);
		statusTable.getColumnModel().getColumn(3).setPreferredWidth((windowWidth*5)/100);
		statusTable.getColumnModel().getColumn(4).setPreferredWidth((windowWidth*25)/100);
		statusTable.getColumnModel().getColumn(5).setPreferredWidth((windowWidth*15)/100);
		
		JScrollPane scrollPane = new JScrollPane(statusTable);
		getContentPane().add(scrollPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
