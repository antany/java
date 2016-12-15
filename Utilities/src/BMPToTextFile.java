import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class BMPToTextFile {
	public static void main(String[] args) throws Exception {
		File imgFile = new File("c:\\simple.bmp");

		DataInputStream buffReader = new DataInputStream(new FileInputStream(
				imgFile));
		int counter = 0;
		int pixelX = 0;
		int pixelY = 0;
		
		int resizeRatio = 1;

		PrintWriter pw = new PrintWriter(new File("c:\\simple.txt"));

		for (String info : infoArrayName) {
			int fin = 0;
			for (int i = 0; i < infoByteLength[counter]; i++) {
				int ch1 = buffReader.read();
				fin = fin + (ch1 << (8 * i));
			}

			if (info.equals("pixelX")) {
				pixelX = fin;
			} else if (info.equals("pixelY")) {
				pixelY = fin;
			}

			System.out.println(info + "--->" + fin);
			counter++;
		}

		int paddedPixel = (1-(1>>(pixelX % 4))) * (4 - pixelX % 4);

		String[][] pixelArray = new String[pixelX][pixelY];

		for (int i = 0; i < pixelY; i++) {
			for (int j = 0; j < pixelX; j++) {
				String blue = CommonUtils.doBaseTenToTarget(buffReader.read(),
						HEX_STRING);
				String green = CommonUtils.doBaseTenToTarget(buffReader.read(),
						HEX_STRING);
				String red = CommonUtils.doBaseTenToTarget(buffReader.read(),
						HEX_STRING);
				String colorRGB = "[" + red + green + blue + "]";
				if (j < pixelX) {
					if (!(Integer.parseInt(blue, 16) > 90)) {
						pixelArray[j][i] = ".";
					} else {
						pixelArray[j][i] = " ";
					}
				} else {
					System.out.println(colorRGB);
				}
			}

			for (int j = 0; j < paddedPixel; j++) {
				buffReader.read();
			}
		}

		for (int i = pixelY - 1; i >= 0; i-=resizeRatio) {
			for (int j = 0; j < pixelX; j+=resizeRatio) {
				pw.print(pixelArray[j][i]);
			}
			pw.println();
		}

		pw.flush();
		pw.close();

		buffReader.close();
	}

	public static String HEX_STRING = "0123456789ABCDEF";

	public static String[] infoArrayName = { "header", "fileSize", "reserved",
			"actualStartAddress", "dibheader", "pixelX", "pixelY",
			"colorPanel", "bitsperpixel", "compression", "rawdatainpixel",
			"hRes", "vRes", "noofcolor", "impcolor" };
	public static int[] infoByteLength = { 2, 4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4,
			4, 4, 4 };

}
