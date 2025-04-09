import java.io.*;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = 0;
		while (true) {
			c = System.in.read();
			if (c == -1 || c == '\n' || c == ' ' || c == '\r') { if (n != 0) break; else continue; }
			n = (n << 3) + (n << 1) + (c & 15);
		} return n;
	}
	
	static int T, ANS, N, X, map[][];
	
	private static boolean isRunwayBuildable(int[] line) {
		
		boolean[] isBuilt = new boolean[N];
		
		for (int i = 0; i < N-1; i++) {

			int height_diff = line[i+1] - line[i];

			// 1. check if same height,
			if (height_diff == 0) continue;
			
			// 2. check if uphill,
			else if (height_diff == 1) {
				for (int j = i; j > i-X; j--) {
					if (j < 0 || line[j] != line[i] || isBuilt[j]) return false;
					isBuilt[j] = true;
				}
			}
			
			// 3. check if downhill,
			else if (height_diff == -1) {
				for (int j = i+1; j <= i+X; j++) {
					if (j >= N || line[j] != line[i+1] || isBuilt[j]) return false;
					isBuilt[j] = true;
				}
			}
			
			// 4. if height difference is more than 1, cannot build,
			else return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {

		int T = read();

		for (int tc = 1; tc <= T; tc++) {
			
			N = read(); X = read(); map = new int[N][N];
			
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) map[i][j] = read();

			ANS = 0;
			
			
			// 1. check rows,
			for (int i = 0; i < N; i++) if (isRunwayBuildable(map[i])) {
				ANS++;
			}
			
			// 2. check cols,
			for (int j = 0; j < N; j++) {
				int[] col = new int[N];
				for (int i = 0; i < N; i++) col[i] = map[i][j]; // copy each column,
				if (isRunwayBuildable(col)) {
					ANS++;
				}
			}
			
			// 3. print out result.
			bw.write("#" + tc + " " + ANS + "\n");
		}
		bw.flush(); bw.close();
	}
}
