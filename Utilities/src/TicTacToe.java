
public class TicTacToe {
	
	public static int maxX=4;
	public static int maxY=4;
	public static char[][] puzzleArray ;
	public static int visitedNodes[][] = new int[4][4];
	public static void main(String[] args) throws Exception{
		String source = CommonUtils.readContentFromFile("/home/antany/myworks/codejam13/test.in");
		String[] lines = source.split("\n");
		int noOfInputs = Integer.valueOf(lines[0]);
		
		int lineCounter=1;
		for(int i=1;i<=noOfInputs;i++){
			puzzleArray = new char[4][4];
			visitedNodes = new int[4][4];
			for(int j=0;j<maxY;j++){
				String currLinx = lines[lineCounter];
				lineCounter++;
				for(int k=0;k<maxX;k++){
					puzzleArray[k][j] = currLinx.charAt(k);
				}
			}
			solveProblem();
			lineCounter++;
		}
	}
	
	public static void solveProblem() {
		int maxXpos = 0;
		int maxYpos = 0;
		int preMaxCounter = 0;
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				if (visitedNodes[i][j]!=1 && puzzleArray[i][j] != '.') {
					int tempCount = floodFill(i, j, 0,'G');
					if (tempCount > preMaxCounter) {
						maxXpos = i;
						maxYpos = j;
						preMaxCounter = tempCount;
					}
				}
			}
		}
		System.out.println(puzzleArray[maxXpos][maxYpos]);
	}
	
	public static int floodFill(int x, int y, int counter, char c) {
		counter++;
		visitedNodes[x][y] = 1;
		
		if (x != maxX - 1 && visitedNodes[x + 1][y] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x + 1][y] ||puzzleArray[x][y]=='T') {
				counter = floodFill(x + 1, y, counter,'H');
			}
		}
		
		if (x != 0 && visitedNodes[x - 1][y] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x - 1][y] || puzzleArray[x][y]=='T') {
				counter = floodFill(x - 1, y, counter,'H');
			}
		}
	
		if (y != 0 && visitedNodes[x][y - 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y - 1] || puzzleArray[x][y]=='T') {
				counter = floodFill(x, y - 1, counter,'V');
			}
		}

		
		if (y != maxY - 1 && visitedNodes[x][y + 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y + 1] || puzzleArray[x][y]=='T') {
				counter = floodFill(x, y + 1, counter,'V');
			}
		}
		
		if (y != 0 && visitedNodes[x+1][y + 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y - 1] || puzzleArray[x][y]=='T') {
				counter = floodFill(x, y - 1, counter,'V');
			}
		}

		
		if (y != maxY - 1 && visitedNodes[x-1][y - 1] != 1) {
			if (puzzleArray[x][y] == puzzleArray[x][y + 1] || puzzleArray[x][y]=='T') {
				counter = floodFill(x, y + 1, counter,'V');
			}
		}
		
		return counter;
	}

	
}
