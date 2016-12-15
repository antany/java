import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		File file = new File("/home/antany/Downloads/google.csv");
		File output = new File("/home/antany/Downloads/out.html");
		PrintWriter pw = new PrintWriter(output);
		BufferedReader bw = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-16"));
		String header = bw.readLine();
		String[] headings = header.split(",");
		@SuppressWarnings("rawtypes")
		Class contactClass = Class.forName("Contact");


		ArrayList<String> heading = new ArrayList<>();

		for (String head : headings) {
			heading.add(head);
			String tempHead = head.replaceAll("[^a-zA-Z0-9]", "");
			String getMethod = "get"+String.valueOf(tempHead.charAt(0)).toUpperCase()+tempHead.substring(1)+"()"; 
			System.out.println(getMethod+"+\":\"+");
		}

		System.exit(0);
		
		@SuppressWarnings("rawtypes")
		Class[] paramString = new Class[1];
		paramString[0] = String.class;

		while (true) {
			String contact = bw.readLine();
			if (contact != null) {
				Contact c = (Contact) contactClass.newInstance();
				String[] contactInfo = contact.split(",");
				int i = 0;

				for (String contactPec : contactInfo) {
					String setMethod = "set"
							+ heading.get(i).replaceAll("[^a-zA-Z0-9]", "");
					Method m = contactClass.getDeclaredMethod(setMethod,
							paramString);
					m.invoke(c, contactPec);
					i++;
				}
				((Contact) c).setActualHeader(heading.get(0));
			} else {
				break;
			}
		}

		pw.flush();
		pw.close();
		bw.close();

	}
}
