import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class PhotoToText {
	public static void main(String[] args) throws Exception {
		File imgFile = new File("/home/antany/Downloads/INDIA PASSPORT, VISA & OCI1.jpg");

		BufferedImage buffReader = ImageIO.read(imgFile);

		PrintWriter pw = new PrintWriter("/home/antany/out.txt");
		
		int pixelX = 0;
		int pixelY = 0;

		pixelY = buffReader.getHeight();
		pixelX = buffReader.getWidth();

		
		int resizeRatio = 10;
		
		String printChar = "smart handsome strongest understanding perfect 	kind stronghold fearless incomparable irresistible divine Tender Bright intelligent seductive attractive energetic Kindhearted Exciting Courageous Amazing  talented stunning romantic Cute Hot creative gentleman impressive Irresistible strong confident powerful";
		int printCharCounter = 0;
		
		System.out.println(pixelX + "," + pixelY);

		for (int i = 0; i < pixelY; i+=resizeRatio) {
			for (int j = 0; j < pixelX; j+=resizeRatio/2) {
				int color = buffReader.getRGB(j, i);
				int red = (color >> 16) & 0xff;
				int green = (color >> 8) & 0xff;
				int blue = color & 0xff;

				if(blue<10 && red < 10 && green < 10){
					pw.print(printChar.charAt(printCharCounter));
					//pw.print(".");
					printCharCounter++;
					if(printCharCounter==printChar.length()){
						printCharCounter = 0;
					}
				}else{
					
					pw.print(" ");
				}

			}
			pw.println();
		}
		
		pw.flush();
		pw.close();
	}

}