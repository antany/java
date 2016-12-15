import java.util.Scanner;

class Main1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.nextLine()).intValue();
		int[][] block = new int[n][n];
		for (int i = 0; i < n; i++) {
			block[i][0] = i + 1;
		}

		try {
			while (true) {
				String[] instruction = in.nextLine().split(" ");
				if (!instruction[0].equalsIgnoreCase("quit")) {
					int a = Integer.valueOf(instruction[1]);
					int b = Integer.valueOf(instruction[3]);
					int methodCount = 0;
					if (instruction[0].equalsIgnoreCase("move")) {
						methodCount = methodCount + 10;
					} else if (instruction[0].equalsIgnoreCase("pile")) {
						methodCount = methodCount + 20;
					}
					if (instruction[2].equalsIgnoreCase("onto")) {
						methodCount = methodCount + 1;
					} else if (instruction[2].equalsIgnoreCase("over")) {
						methodCount = methodCount + 2;
					}

					switch (methodCount) {
					case 11:
						moveOnto(block, a, b);
						break;
					case 12:
						moveOnto(block, a, b);
						break;
					case 21:
						pileOnto(block, a, b);
						break;
					case 22:
						pileOver(block, a, b);
						break;
					default:
						break;
					}
					;

				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.exit(0);
		}
		in.close();
		printArray(block);
	}

	public static void moveOnto(int[][] block, int a, int b) {

	}

	public static void moveOver(int[][] block, int a, int b) {

	}

	public static void pileOnto(int[][] block, int a, int b) {

	}

	public static void pileOver(int[][] block, int a, int b) {

	}

	public static void printArray(int[][] block) {
		int n = block.length;
		for (int i = 0; i < n; i++) {
			System.out.print(i + ":");
			int m = block[i].length;
			for (int j = 0; j < m; j++) {
				if (block[i][j] != 0) {
					System.out.print(" " + (block[i][j] - 1));
				}
			}
			System.out.println("");
		}
	}
	
	
}
