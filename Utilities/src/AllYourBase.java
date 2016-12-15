import java.io.File;


public class AllYourBase {
	public static void main(String[] args) throws Exception {

		String inputFile = "c:\\R1A-large-practice.in";
		String outputFile = "c:\\\\R1A-large-practice.out";
		String input = CommonUtils.readContentFromFile(inputFile);
		int noOflines = Integer.valueOf(CommonUtils.getSpecificLine(input, 1));
		File file = new File(outputFile);
		file.delete();
		
		for(int i=2;i<=noOflines+1;i++){
			String actual = CommonUtils.getSpecificLine(input, i);
			String src = makeSrcLanguageString(CommonUtils.removeDuplicateCharacters(actual));
			CommonUtils.writeContent(outputFile,("Case #"+(i-1)+": "+CommonUtils.doBaseTenConversion(actual, src)));	
		}
	}

	public static String makeSrcLanguageString(String src) {
		if(src.length()==1){
			return 0+""+src.charAt(0);
		}else{
			String newStr = src.charAt(1) + "" + src.charAt(0);
			for (int i = 2; i < src.length(); i++) {
				newStr = newStr + src.charAt(i);
			}
			return newStr;
		}
	}
}
