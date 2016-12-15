import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BatchMKVMaker {
	public static void main(String[] args) throws Exception {
		String rootFolder ="/media/antany/My Passport/Videos/Tamil";
		iterateFiles(new File(rootFolder));
	}

	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					iterateFiles(file);
				}else{
					if(file.getName().equalsIgnoreCase("VIDEO_TS.IFO")){
						String folderName = file.getParentFile().getAbsolutePath();
						String movieName = file.getParentFile().getName();
						System.out.println("Processing folder "+folderName+"...");
						String title  = findPossibleTitle(folderName);
						System.out.println("Title Found = "+title);
						convertDVDMovieToMKV(folderName, null, "0", movieName);
						
					}
				}
			}

		}
	}

	public static String findPossibleTitle(String folder) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String titleNum = "all";
		String[] command = { "makemkvcon", "info", "file:"+folder };
		Process p = rt.exec(command);
		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = bf.readLine()) != null) {
			if (line.matches("Title #.* was added.*")) {
				String timePattern = "(\\d{1,2}):(\\d{2}):(\\d{1,2})";
				Pattern pattern = Pattern.compile(timePattern);
				Matcher m = pattern.matcher(line);
				while (m.find()) {
					int hour = Integer.parseInt(m.group(1));
					int min = Integer.parseInt(m.group(2));
					int total = (hour * 100) + min;
					if (total > 30) {
						titleNum = line.replaceAll("Title.*?(\\d+).*", "$1");
						System.out.println(titleNum);
					}
				}
			}
		}
		return titleNum;
	}
	
	
	public static void convertDVDMovieToMKV(String srcfolderName, String targetFolder, String title, String movieName) throws IOException{
		Runtime rt = Runtime.getRuntime();
		targetFolder = "/media/antany/My Passport/mkvs";
		String []	commands = {"makemkvcon","mkv","file:"+srcfolderName,title,targetFolder };
		Process p = rt.exec(commands);
		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = bf.readLine()) != null) {
			System.out.println(line);
		}
		
		File file = new File(targetFolder+"/title00.mkv");
		file.renameTo(new File(targetFolder+"/"+movieName+".mkv"));
		
	}
	
}
