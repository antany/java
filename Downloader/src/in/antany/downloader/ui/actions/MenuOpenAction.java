package in.antany.downloader.ui.actions;

import in.antany.downloader.exception.IncorrectURLException;
import in.antany.downloader.util.DownloaderUtils;
import in.antany.downloader.util.MakeDownloadDecisionAndTriggerDownload;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MenuOpenAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

		String urlString = JOptionPane.showInputDialog("Please enter URL");
		URL url;

		if (urlString != null) {
			try {
				url = new URL(urlString);
				if (!DownloaderUtils.isValidURL(url)) {
					throw new IncorrectURLException();
				}
				
				String fileName = urlString.replaceAll("http://.*/", ""); 
				
				JFileChooser jfc = new JFileChooser();
				jfc.setSelectedFile(new File(fileName));
				int userSelection = jfc.showSaveDialog(null);
				String targetFile=null;
				if (userSelection == JFileChooser.APPROVE_OPTION) { 
					targetFile = jfc.getSelectedFile().getPath();
				}
				MakeDownloadDecisionAndTriggerDownload mk = new MakeDownloadDecisionAndTriggerDownload(url,targetFile);
				mk.beginDownload();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Incorrect URL");
			}

		}
	}
}
