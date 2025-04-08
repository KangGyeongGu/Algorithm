import java.util.*;
import java.io.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
	    int c; while ((c = System.in.read()) != -1) if ('0' <= c && c <= '9') return c - '0';
	    return -1;
	}
	
	static boolean isComplete;
	static int[][] map = new int[9][9];
	static boolean[][][] iv = new boolean[3][9][10];
	static List<int[]> inComplete = new ArrayList<>();
	
	private static void init() throws Exception {
		for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) {
			int in = read();
			map[i][j] = in;
			if (in == 0) inComplete.add(new int[] { i, j });
			else {
				int squareIdx = (i / 3) * 3 + (j / 3);
				iv[0][i][in] = true;
				iv[1][j][in] = true;
				iv[2][squareIdx][in] = true;
			}
		}
	}

	private static void printer() throws Exception {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) bw.write(Integer.toString(map[i][j])); 
			bw.write("\n");
		}
	}
	
	private static void backtrack(int depth) throws Exception {
		if (depth == inComplete.size()) {
			printer();
			isComplete = true;
			return;
		}
		
		if (isComplete) return;
		
		int[] cur = inComplete.get(depth);
		int x = cur[0], y = cur[1], squareIdx = (x/3)*3 + (y/3);
		
		for (int n = 1; n <= 9; n++) {
			if (iv[0][x][n] || iv[1][y][n] || iv[2][squareIdx][n]) continue; // check if number n is already used,
			
			iv[0][x][n] = iv[1][y][n] = iv[2][squareIdx][n] = true; // if not used, use this number,
			
			map[x][y] = n; // mark on the puzzle,
			
			backtrack(depth+1); // backtrack,
			
			if (isComplete) return; // pruning,
			
			iv[0][x][n] = iv[1][y][n] = iv[2][squareIdx][n] = false; // cancel this number for backtrack,
			
			map[x][y] = 0; // cancel this number from the puzzle,
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtrack(0);
		bw.flush(); bw.close();
	}
}
