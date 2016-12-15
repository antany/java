import java.awt.Rectangle;

import java.awt.Robot;

import java.awt.Toolkit;

import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

public class QueryBot {

	public static void main(String[] args) throws Exception {

		try {

			ProcessBuilder proc = new ProcessBuilder(

			"gedit");

			proc.start();

		} catch (Exception e) {

			e.printStackTrace();

			System.exit(1);

		}

		Robot robot = new Robot();

		delayInSeconds(robot, 10);

		altKeyPress(robot, KeyEvent.VK_S);

		delayInSeconds(robot, 1);

		keyPress(robot, KeyEvent.VK_N);

	}

	public static void typeString(Robot robot, String string) {

		for (int i = 0; i < string.length(); i++) {

			int chr = string.toUpperCase().charAt(i);

			if (chr == ((int) string.charAt(i))) {

				keyPress(robot, chr, true);

			} else {

				keyPress(robot, chr);

			}

		}

	}

	public static void keyPress(Robot robot, int keyCode) {

		robot.keyPress(keyCode);

		robot.keyRelease(keyCode);

	}

	public static void keyPress(Robot robot, int keyCode, boolean shifKey) {

		if (shifKey) {

			robot.keyPress(KeyEvent.VK_SHIFT);

		}

		keyPress(robot, keyCode);

		if (shifKey) {

			robot.keyRelease(KeyEvent.VK_SHIFT);

		}

	}

	public static void altKeyPress(Robot robot, int keyCode) {

		robot.keyPress(KeyEvent.VK_ALT);

		robot.keyPress(keyCode);

		robot.keyRelease(keyCode);

		robot.keyRelease(KeyEvent.VK_ALT);

	}

	public static void delayInSeconds(Robot robot, int seconds) {

		seconds = seconds * 1000;

		robot.delay(seconds);

	}

	public static void triggerTab(Robot robot, int tabCount) {

		for (int i = 1; i <= tabCount; i++) {

			robot.keyPress(KeyEvent.VK_TAB);

			robot.keyRelease(KeyEvent.VK_TAB);

		}

	}

	public static void triggerEnter(Robot robot, int enterCount) {

		for (int i = 1; i <= enterCount; i++) {

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

		}

	}

	public static void caputeScreenShot(Robot robot, int x, int y, int height,

	int width, String fileName) throws Exception {

		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(x,

		y, width, height));

		ImageIO.write(screenShot, "JPG", new File(fileName));

	}

	public static void captureEntireScreen(Robot robot, String fileName)

	throws Exception {

		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(

		Toolkit.getDefaultToolkit().getScreenSize()));

		ImageIO.write(screenShot, "JPG", new File(fileName));

	}

}