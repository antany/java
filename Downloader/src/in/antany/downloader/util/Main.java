package in.antany.downloader.util;

import in.antany.downloader.ui.MainFrame;

import java.net.URL;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
				for(int i=0;i<4;i++){
					try{
						MakeDownloadDecisionAndTriggerDownload mk =
								new MakeDownloadDecisionAndTriggerDownload(new URL("http://localhost:8080/antany/jdk-8-ea-bin-b103-windows-i586-15_aug_20131.exe"),"c:/"+i+"jdk-8-ea-bin-b103-windows-i586-15_aug_20131.exe");
						mk.beginDownload();
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}
		});
	}
}
