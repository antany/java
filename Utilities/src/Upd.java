import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 
 * @author antany
 */
public class Upd {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static int x = 0;
	public static int y = 0;
	public static int sqsize = 40;
	public static Robot robot;

	public static void main(String[] args) throws Exception {
		robot = new Robot();
		System.out.println("Bot Started");
		// Thread.sleep(2000);

		// Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.);
		while (!Toolkit.getDefaultToolkit().getLockingKeyState(
				KeyEvent.VK_CAPS_LOCK))
			;

		x = MouseInfo.getPointerInfo().getLocation().x;
		y = MouseInfo.getPointerInfo().getLocation().y;

		colorSelectionTest();


		while (true) {

			//dt = new Date();
			//long currentTime = dt.getTime();
			//System.out.println(new Date().getTime());
			while (!Toolkit.getDefaultToolkit().getLockingKeyState(
					KeyEvent.VK_CAPS_LOCK));
			solveProblemBot();

			//System.out.println(new Date().getTime());

			//if ((currentTime - startTime) / 1000 > 150) {
				// System.out.println((currentTime - startTime) / 1000);
//				break;
	//		}
		}
	}

	public static void solveProblemBot() throws Exception {

		//int a = 0;
		//int b = 0;

		//int xMax = x + (sqsize * 9);

		//int clickCount = 0;
		
		//long startTime;
		//long endTime;
		//startTime = System.currentTimeMillis();
		for (int j = (y + (sqsize * 8)); j >= y; j -= sqsize) {
			//b++;
			//a = 0;
			for (int i = x; i < (x + (sqsize * 10)); i += sqsize) {
				//a++;
				// System.out.println(i + ":" + j);

				boolean ignoreUpOne = false;
				boolean ignoreUpTwo = false;
				boolean ignoreRightOne = false;
				boolean ignoreRightTwo = false;
				boolean ignoreCrossPlus = false;
				boolean ignoreCrossMinus = false;

				int currColor = getColor(i, j);

				int upOne = getColor(i, j - sqsize);
				int upTwo = getColor(i, j - (sqsize * 2));
				int rightOne = getColor(i + sqsize, j);
				int rightTwo = getColor(i + (sqsize * 2), j);
				int crossPlus = getColor(i + sqsize, j - sqsize);
				int crossMinus = getColor(i - sqsize, j - sqsize);

				int testCount = 0;

				if (j == y) {
					ignoreUpOne = true;
					ignoreUpTwo = true;
				}
				if (j == (y + sqsize)) {
					ignoreUpTwo = true;
				}
				if (i == x) {
					ignoreCrossMinus = true;
				}
				if (i == x + (sqsize * 9)) {
					ignoreRightOne = true;
					ignoreRightTwo = true;
					ignoreCrossPlus = true;
				}
				if (i == x + (sqsize * 9)) {
					ignoreRightTwo = true;
				}

				if (currColor == upOne && !ignoreUpOne
						&& currColor == crossPlus && !ignoreCrossPlus) {
					testCount++;
				} else if (currColor == upOne && !ignoreUpOne
						&& currColor == upTwo && !ignoreUpTwo) {
					testCount++;
				} else if (currColor == upOne && !ignoreUpOne
						&& currColor == rightOne && !ignoreRightOne) {
					testCount++;
				} else if (currColor == upOne && !ignoreUpOne
						&& currColor == crossMinus && !ignoreCrossMinus) {
					testCount++;
				} else if (currColor == rightOne && !ignoreRightOne
						&& currColor == rightTwo && !ignoreRightTwo) {
					testCount++;
				} else if (currColor == rightOne && !ignoreRightOne
						&& currColor == crossPlus && !ignoreCrossPlus) {
					testCount++;
				}
				
				while (!Toolkit.getDefaultToolkit().getLockingKeyState(
						KeyEvent.VK_CAPS_LOCK));
				if (testCount > 0) {
					robot.mouseMove(i, j);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

				}
			}
		}
		//endTime = System.currentTimeMillis();
		//System.out.println("Time taken for a Single Scan"+(endTime-startTime)/1000);
	}

	public static int getColor(int x, int y) throws Exception {
		return getColor(x, y, false);
	}

	public static int getColor(int x, int y, boolean printColor)
			throws Exception {

		Color color = robot.getPixelColor(x, y);
		if (printColor) {
			System.out.println(color);
		}
		return color.getRGB();
	}

	public static void colorSelectionTest() throws Exception {
		for (int j = (y + (sqsize * 8)); j >= y; j -= sqsize) {
			for (int i = x; i < (x + (sqsize * 10)); i += sqsize) {
				getColor(i, j, true);
			}
		}
	}
}
