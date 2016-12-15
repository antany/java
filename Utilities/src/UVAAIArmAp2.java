import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.nextLine()).intValue();
		String[] block = new String[n];
		int[] location = new int[n];
		// Initializing values
		for (int i = 0; i < n; i++) {
			block[i] = "" + i;
			location[i] = i;
		}
		while (true) {
			String cmd = in.nextLine();
			String[] command = cmd.split("\\s");
			if (command[0].equalsIgnoreCase("quit")) {
				break;
			} else {
				int a = Integer.valueOf(command[1]);
				int b = Integer.valueOf(command[3]);
				if(a<n && b<=n && a>=0 && b>=0){
				if(location[a]!=location[b] && a!=b){
					if(command[0].equalsIgnoreCase("move")){
						moveInitPositions(a, block, location);
					}
					if(command[2].equalsIgnoreCase("onto")){
						moveInitPositions(b, block, location);
					}
					
						putAonB(a, b, block, location);
					
				}
				}
			}
		}
		in.close();
		printBlocks(block);
		System.exit(0);
	}

	public static void printBlocks(String[] block) {
		int arrayLength = block.length;
		for (int i = 0; i < arrayLength; i++) {
			System.out.println(i + ": " + block[i]);
		}
	}

	public static void moveInitPositions(int a, String[] block, int[] location) {
		String str = block[location[a]];
		String[] befAft = str.split("(?<="+String.valueOf(a)+")");
		block[location[a]] = befAft[0].trim();
		if (befAft.length > 1) {
			String[] afterElements = befAft[1].trim().split(" ");
			for (int i = 0; i < afterElements.length; i++) {
				int loc = (Integer.valueOf(afterElements[i]));
				block[loc] = afterElements[i];
				location[loc] = loc;
			}
		}
	}

	public static void putAonB(int a, int b, String[] block, int[] location) {
		int aLoc = location[a];
		int bLoc = location[b];
		String strA = block[aLoc].trim();
		String strB = block[bLoc].trim();
		strB = strB.trim() + " " + strA.substring(strA.indexOf("" + a)).trim();
		block[bLoc] = strB;
		block[aLoc] = strA.replace(strA.substring(strA.indexOf("" + a)), "")
				.trim();
		String[] newStrBElements = strB.split("\\s+");
		for (String str : newStrBElements) {
			location[Integer.valueOf(str)] = bLoc;
		}
	}
}
