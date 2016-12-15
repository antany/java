import java.io.File;

public class AlientNumbers2 {
	public static String inputFile = "C:\\A-large-practice.in";
	public static String outputFile = "C:\\A-large-practice1.out";

	public static void main(String[] args) throws Exception {
		String inputSrc = CommonUtils.readContentFromFile(inputFile);
		File file = new File(outputFile);
		file.delete();
		int noOfInputs = Integer.valueOf(CommonUtils.getSpecificLine(inputSrc,
				1));
		for (int i = 2; i <= noOfInputs + 1; i++) {
			String[] inputArray = CommonUtils.getSpecificLine(inputSrc, i)
					.split("\\s");
			CommonUtils.writeContent(
					outputFile,
					doNumericConversion(inputArray[0], inputArray[1],
							inputArray[2], i - 1));
		}
	}

	public static String doNumericConversion(String inp, String sourceLanguage,
			String targetLanguage, int lineNumber) {
		int srcBase = sourceLanguage.length();
		int tarBase = targetLanguage.length();
		String tarValue = "";
		int base10Val = 0;
		for (int i = 0; i < inp.length(); i++) {
			base10Val *= srcBase;
			base10Val += sourceLanguage.indexOf(inp.charAt(i));
		}

		while (base10Val != 0) {
			tarValue = targetLanguage.charAt(base10Val % tarBase) + tarValue;
			base10Val /= tarBase;
		}
		return ("Case #" + lineNumber + ": " + tarValue);
	}
}

// Case #{lineNumber}: {target}
