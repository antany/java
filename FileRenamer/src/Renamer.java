import java.io.File;
import java.util.Scanner;

public class Renamer {
	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					iterateFiles(file);
				}else{
					if(file.getName().matches(".*?\\.((mp4)|(avi)|(srt)|(sub)|(mkv))")){
						//System.out.println(file.getAbsolutePath());
						renamer(file);
					}
					//renamer(file);
				}
			}
		}
	}
	
	public static void renamer(File file) throws Exception{
		String actualFileName = file.getName();
		//String actualFolder = file.getParent();
		String newFolder = "/media/antany/My Passport/Media/Movies/English";
		String newName = actualFileName.replaceAll("\\]", ")");
		newName = newName.replaceAll("\\[", "(");
		newName = newName.replaceAll("\\.(?=.*\\.)", " ");
		newName = newName.replaceAll("(.*?[0-9]{4}).*(\\.((mp4)|(avi)|(srt)|(sub)|(mkv)))", "$1$2");
		newName = newName.replaceAll("([0-9]{4})", "($1)");
		String newFile = newFolder+"/"+newName;
		if(!newFile.equals(file.getAbsolutePath())){
			System.out.println("Old:"+file.getAbsolutePath());
			System.out.println("New:"+newFile);
			System.out.println("Do you want to proceed above changes (y/n)?");
			Scanner in = new Scanner(System.in);
			String resp = in.nextLine();
			if(resp.equalsIgnoreCase("y")){
				file.renameTo(new File(newFile));
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("/media/antany/My Passport/Media_OL/topc");
		iterateFiles(file);
	}
}
