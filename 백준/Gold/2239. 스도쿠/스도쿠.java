import java.util.*;
import java.io.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c;
		while ((c = System.in.read()) != -1) if ('0' <= c && c <= '9') return c - '0';
		return -1;
	}

	static int[][] map = new int[9][9];
	static int[] rowBit = new int[9], colBit = new int[9], sqrBit = new int[9];
	static List<int[]> inComp = new ArrayList<>();
	static boolean done;

	private static void init() throws Exception {
		for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) {
			int n = read();
			map[i][j] = n;
			if (n == 0) inComp.add(new int[] { i, j });
			else {
				int sqr = (i / 3) * 3 + (j / 3);
				rowBit[i] |= 1 << n;
				colBit[j] |= 1 << n;
				sqrBit[sqr] |= 1 << n;
			}
		}
	}

	private static void printer() throws Exception {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) bw.write(Integer.toString(map[i][j]));
			bw.write("\n");
		}
	}

	private static void bt(int d) throws Exception {
		if (done) return;
		if (d == inComp.size()) {
			printer();
			done = true;
			return;
		}
		
		int[] cur = inComp.get(d);
		int x = cur[0], y = cur[1], s = (x / 3) * 3 + (y / 3);

		for (int n = 1; n <= 9; n++) {
			int bit = 1 << n;
			if ((rowBit[x] & bit) != 0 || (colBit[y] & bit) != 0 || (sqrBit[s] & bit) != 0) continue;

			map[x][y] = n;
			rowBit[x] ^= bit;
			colBit[y] ^= bit;
			sqrBit[s] ^= bit;

			bt(d + 1);
			if (done) return;

			map[x][y] = 0;
			rowBit[x] ^= bit;
			colBit[y] ^= bit;
			sqrBit[s] ^= bit;
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		bt(0);
		bw.flush(); bw.close();
	}
}
