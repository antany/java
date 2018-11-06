import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class CommonUtils {

	public static void main(String[] args) throws Exception {
		writeContent("c:\\test",
				readContentFromFile("c:\\test"));
	}

	public static String readContentFromFile(String fileName) throws Exception {
		File srcFile = new File(fileName);
		char[] charContent = new char[(int) srcFile.length()];
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				srcFile));
		bufferedReader.read(charContent);
		bufferedReader.close();
		return String.copyValueOf(charContent);
	}

	public static int getLineCount(String src) {
		return src.split("\\n").length;
	}

	public static void writeContent(String fileName, String src)
			throws Exception {
		PrintWriter pw = new PrintWriter(new FileWriter((new File(fileName)),
				true));
		pw.println(src);
		pw.flush();
		pw.close();
	}

	public static String getSpecificLine(String src, int lineNumber)
			throws Exception {
		try {
			return src.split("\\n")[lineNumber - 1];
		} catch (Exception ioe) {
			throw new Exception("Invalid Line Number");
		}
	}

	public static String doNumericConversion(String inp, String sourceLanguage,
			String targetLanguage) {
		return doBaseTenToTarget(doBaseTenConversion(inp, sourceLanguage),
				targetLanguage);
	}

	public static long doBaseTenConversion(String inp, String sourceLanguage) {
		int srcBase = sourceLanguage.length();
		long base10Val = 0;
		for (int i = 0; i < inp.length(); i++) {
			base10Val *= srcBase;
			base10Val += sourceLanguage.indexOf(inp.charAt(i));
		}
		return base10Val;
	}

	public static String doBaseTenToTarget(long base10Val, String targetLanguage) {
		String tarValue = "";
		int tarBase = targetLanguage.length();
		while (base10Val != 0) {
			tarValue = targetLanguage.charAt((int) base10Val % tarBase)
					+ tarValue;
			base10Val /= tarBase;
		}
		return tarValue;
	}

	public static String removeDuplicateCharacters(String inp) {
		StringBuffer uniqueString = new StringBuffer();
		for (int i = 0; i < inp.length(); i++) {
			if (uniqueString.indexOf(String.valueOf(inp.charAt(i))) == -1) {
				uniqueString.append(inp.charAt(i));
			}
		}
		return uniqueString.toString();
	}
	public static void iterateFiles(File startFolder) throws Exception {
		if (startFolder.isDirectory()) {
			File[] files = startFolder.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					iterateFiles(file);
				}else{
					System.out.println(file.getAbsolutePath());
				}
			}
		}
	}
	
	public static void downloadFile(String fileURL, String destinationPath)
			throws Exception {
		DataOutputStream os = null;

		DataInputStream is = null;

		try {
			String protocol = fileURL.replaceAll(":.*", "");
			String baseURL = fileURL.replaceAll("http://([a-zA-Z.0-9]*?)/.*",
					"$1");
			String urlPath = fileURL.replaceAll("http://[a-zA-Z.0-9]*?(/.*)",
					"$1");

			URI uri = new URI(protocol, baseURL, urlPath, null);
			URL url = uri.toURL();

			HttpURLConnection fileURLConnection = (HttpURLConnection) url
					.openConnection();

			System.out.println("Started Downloading " + fileURL + " to "
					+ destinationPath);

			os = new DataOutputStream(new FileOutputStream(new File(
					destinationPath)));

			int fileLength = fileURLConnection.getContentLength();

			is = new DataInputStream(fileURLConnection.getInputStream());

			byte[] read = new byte[4096];
		

			int n;
			int progress = 0;

			while ((n = is.read(read)) != -1) {
				os.write(read, 0, n);
				progress = progress + n;
				System.out.print("Downloading in progress...."
						+ (int) ((float) progress / fileLength * 100) + " %\r");
			}

			System.out.println("\nDownload Sucessful");
		} catch (Exception e) {
			System.err.println("\nDownload failed.." + fileURL);
			e.printStackTrace();
		} finally {
			os.flush();
			is.close();
			os.close();
		}

	}
	
	public static String getPageContent(String pageURL) throws Exception {
		URL downloadURL = new URL(pageURL);

		URLConnection url = downloadURL.openConnection();

		BufferedReader urlReader = new BufferedReader(new InputStreamReader(
				url.getInputStream()));

		String urlContent = "";
		String tempSource;

		while ((tempSource = urlReader.readLine()) != null) {
			urlContent = urlContent + tempSource;
		}

		return (urlContent);
	}
	
}
