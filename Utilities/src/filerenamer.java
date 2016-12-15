import java.io.File;

public class filerenamer {
	public static void main(String[] args) throws Exception{
		iterateFiles(new File("E:\\Media_OL\\Movies\\English\\Barbie Movies  Collection"));
	}
	
	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					iterateFiles(file);
				}else{
					
					System.out.println(file.getAbsolutePath());
					reNamer(file);
				}
			}
		}
	}
	
	public static void reNamer(File file){
		String actualFileName = file.getName();
		String actualFolderName = file.getParentFile().getAbsolutePath();
		String newFileName = actualFileName.replaceAll("\\[", "(");
		newFileName = newFileName.replaceAll("\\]", ")");
		File newFile = new File(actualFolderName+"\\"+newFileName);
		file.renameTo(newFile);
	}
}
