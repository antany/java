import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LargestGroupArray {
	public static int[][] puzzleArray = { 
		    { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 9, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 8, 9, 1, 2, 3, 4, 5, 6, 7 },
			{ 7, 8, 9, 1, 2, 3, 4, 5, 6 },
			{ 6, 7, 8, 9, 1, 1, 3, 4, 5 },
			{ 5, 6, 7, 8, 9, 1, 1, 3, 4 },
			{ 4, 5, 6, 6, 8, 9, 1, 2, 3 },
			{ 3, 4, 5, 6, 7, 8, 9, 1, 2 },
			{ 2, 3, 4, 5, 6, 7, 8, 9, 1 },
			{ 1, 2, 3, 4, 5, 6, 7, 8, 9 } 
			};
	public static int[][] visitedNodes = new int[10][9];

	public static int[] uniqueColorArray = new int[0];

	public static int maxX = 10;
	public static int maxY = 9;

	public static int initX = 0;
	public static int initY = 0;

	
	public static int i = 0;

	public static Robot robot;

	public static void main(String[] args) throws Exception {

		System.out.println("Bot Started..");
		
		while (!Toolkit.getDefaultToolkit().getLockingKeyState(
				KeyEvent.VK_CAPS_LOCK))
			;
		
		initX = MouseInfo.getPointerInfo().getLocation().x;
		initY = MouseInfo.getPointerInfo().getLocation().y;
		robot = new Robot();
		resetPuzzleArray(1);
		//printArray(puzzleArray);
		startBot();
		
		//printUniqueArray();

	}

	public static void resetVisitedNodes() {
		visitedNodes = new int[10][9];
	}
	
	public static void startBot(){
		
		while(true){
			while (!Toolkit.getDefaultToolkit().getLockingKeyState(
					KeyEvent.VK_CAPS_LOCK))
				;
			solveProblem();
			resetPuzzleArray(2);
			resetVisitedNodes();
			
		}
		
	}

	public static void resetPuzzleArray(int cnt) {
		
		
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				puzzleArray[i][j] = robot.getPixelColor(initX + (40 * i),
						initY + (40 * j)).getRGB();
				if (cnt == 2) {
					if (isNewColor(puzzleArray[i][j])) {
						/*int red =puzzleArray[i][j]&0xFF;
						int blue = ((puzzleArray[i][j]>>8)&0xFF) ;
						int green =   ((puzzleArray[i][j]>>16)&0xFF);
						
						if(red!=blue && blue!=green && red!=green){*/
							//System.out.println(red+":"+blue+":"+green);
							//triggerMouseClickEvent(initX + (40 * i), initY+ (40 * j));
						/*}*/
					}
				} else {
					addColorToUniqueArray(puzzleArray[i][j]);
				}
			}
		}
	}

	public static void addColorToUniqueArray(int colorInt) {
		int tempArray[];
		boolean isExists = false;
		for (int i = 0; i < uniqueColorArray.length; i++) {
			if (uniqueColorArray[i] == colorInt) {
				isExists = true;
			}
		}
		if (!isExists) {
			tempArray = new int[uniqueColorArray.length + 1];
			int cnt = 0;
			for (int i = 0; i < uniqueColorArray.length; i++) {
				tempArray[i] = uniqueColorArray[i];
				cnt++;
			}
			tempArray[cnt] = colorInt;
			uniqueColorArray = tempArray;
		}
	}

	public static boolean isNewColor(int colorInt) {
		for (int i = 0; i < uniqueColorArray.length; i++) {
			if (uniqueColorArray[i] == colorInt) {
				return false;
			}
		}
		return true;
	}

	public static void printUniqueArray() {
		for (int i = 0; i < uniqueColorArray.length; i++) {
			System.out.println(uniqueColorArray[i]);
		}
	}

	public static void solveProblem() {
		int maxXpos = 0;
		int maxYpos = 0;
		int preMaxCounter = 0;
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				if (visitedNodes[i][j] != 1) {
					int tempCount = floodFill(i, j, 0);
					if (tempCount > preMaxCounter) {
						maxXpos = i;
						maxYpos = j;
						preMaxCounter = tempCount;
					}
				}
			}
		}if(preMaxCounter>2){
			triggerMouseClickEvent(initX + (40 * maxXpos), initY + (40 * maxYpos));
		}
	}

	public static void triggerMouseClickEvent(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}

	public static void printArray(int[][] arr) {
		System.out.println("=============================");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(":" + arr[i][j]);
			}
			System.out.println();
		}
	}

	public static int floodFill(int x, int y, int counter) {
		counter++;
		visitedNodes[x][y] = 1;
		if (x != 0 && visitedNodes[x - 1][y] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x - 1][y]) {
				counter = floodFill(x - 1, y, counter);
			}
		}
		if (y != 0 && visitedNodes[x][y - 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y - 1]) {
				counter = floodFill(x, y - 1, counter);
			}
		}

		if (x != maxX - 1 && visitedNodes[x + 1][y] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x + 1][y]) {
				counter = floodFill(x + 1, y, counter);
			}
		}
		if (y != maxY - 1 && visitedNodes[x][y + 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y + 1]) {
				counter = floodFill(x, y + 1, counter);
			}
		}
		return counter;
	}
	
	
}
