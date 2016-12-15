import java.io.File;

import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;

public class JavaTagEditor {
	public static void main(String[] args) throws Exception{
		iterateFiles(new File("F:\\set2"));
	}
	
	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					iterateFiles(file);
					reNamer(file);
				}else{
					
					System.out.println(file.getAbsolutePath());
					reNamer(file);
					//tagEditor(file);
				}
			}
		}
	}
	
	public static void reNamer(File file){
		String actualFileName = file.getName();
		String actualFolderName = file.getParentFile().getAbsolutePath();
		String newFileName = actualFileName.replaceAll("_", " ");
		newFileName = newFileName.replaceAll("\\(.*\\)", "");
		newFileName = newFileName.replaceAll("-", "");
		newFileName = newFileName.replaceAll("StarMusiQ.Com", "");
		newFileName = newFileName.replaceAll("VmusiQ.Com", "");
		File newFile = new File(actualFolderName+"\\"+newFileName);
		file.renameTo(newFile);
	}
	
	public static void tagEditor(File file) throws Exception{
		 MP3File mp3file = new MP3File(file);
		 AbstractMP3Tag mp3Tag;
		 if(mp3file.hasID3v2Tag()){
			 mp3Tag = mp3file.getID3v1Tag();
			 mp3Tag.setAlbumTitle("asdfasdf");
			 mp3Tag.setSongTitle("testing");
			 mp3file.setID3v1Tag((ID3v1)mp3Tag);
		 }
		 mp3file.save();
		 System.exit(0);
		 
	}
}
