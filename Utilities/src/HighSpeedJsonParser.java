
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Stack;

public class HighSpeedJsonParser {

	public static String jsonFilePath = "C:/test1.json";

	public static String testJsonFile = "C:/Antany/test.json";

	public static Stack<String> expressions = new Stack<>();

	public static char[] keyWords = { '[', ']', '{', '}',',' };

	private static OutputStream os = null;

	private static int byteCount = 25000;
	
	
	private static int currCount = 0;
	private static byte[] outByte = new byte[byteCount + 5000];

	public static void main(String[] args) throws Exception {

		jsonFilePath = args[0];

		System.out.println("processing..."+jsonFilePath);
		
		File jsonFile = new File(jsonFilePath);
		InputStream is = new FileInputStream(jsonFile);
		byte[] chBuff = new byte[byteCount];
		int count = 0;
		long currentTime = System.currentTimeMillis();
		int n;
		
		Arrays.sort(keyWords);
		
		

		os = new FileOutputStream("jsonFilePath_formatted");

		boolean isPopped = false;
		boolean prePopped = false;
		while ((n = is.read(chBuff)) != -1) {
			for (int i = 0; i < n; i++) {
				int k = Arrays.binarySearch(keyWords, (char) chBuff[i]);
				

				if(isPopped){
					outByte[currCount] = '\n';
					currCount++;
					if (!expressions.isEmpty()) {
						printTab(expressions.size());
					}
					isPopped = false;
					prePopped = true;
				}	
			
				try{


					if (k >= 0) {

						String expresssion = String.valueOf(keyWords[k]);
						String preExpression = null;
						if (k != 0) {
							preExpression = String.valueOf(keyWords[k - 1]);
						}
					
						if (",".equals(expresssion)){
							if(prePopped&&currCount>expressions.size()-1){
								currCount= currCount-expressions.size();
								prePopped = false;
							}
						}else if (expressions.isEmpty() || expressions.peek().equals(expresssion)) {
							if (!expressions.isEmpty()) {
								outByte[currCount] = '\n';
								currCount++;
							}
							expressions.push(expresssion);
							printTab(expressions.size());
						} else if (!expressions.peek().equals(preExpression)) {
							outByte[currCount] = '\n';
							currCount++;
							expressions.push(expresssion);
							printTab(expressions.size());
						} else {
							expressions.pop();
							isPopped = true;
						}
					}
				
					outByte[currCount] = chBuff[i];
					currCount++;
				}catch(Exception e){
					System.out.println(currCount);
					System.out.println(i);
					throw e;
				}
				
				if (currCount >= byteCount) {
					os.write(outByte,0,currCount);
					currCount = 0;
				}

				count++;
			}

		}
		long diff = (System.currentTimeMillis() - currentTime);
		System.out.println(count + ">>>" + diff);
		os.write(outByte, 0, currCount);
		os.flush();
		os.close();
		is.close();
	}

	private static void printTab(int noOfTabs) throws Exception {

		for (int i = 0; i < noOfTabs-1; i++) {
			outByte[currCount] = '\t';
			currCount++;
		}
	}
}
