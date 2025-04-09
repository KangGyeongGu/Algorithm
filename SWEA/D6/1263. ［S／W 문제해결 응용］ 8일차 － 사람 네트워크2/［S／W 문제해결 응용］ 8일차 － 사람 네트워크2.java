import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int read() throws Exception {
		int c = System.in.read(), n = 0, sign = 1;
		while (c <= 32) c = System.in.read();
		if (c == '-') { sign = -1; c = System.in.read(); }
		n = c & 15; while ((c = System.in.read()) >= '0') n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read(); return n * sign;
	}

	public static void main(String[] args) throws Exception {
		
		int INF = 1_000_000_000, T = read();
		for (int tc = 1; tc <= T; tc++) {
			
			int N = read(), dist[][] = new int[N][N], cc[] = new int[N];
			
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) {
				dist[i][j] = (i==j) ? 0 : INF;
			}
			
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if (read() == 1) dist[i][j] = 1;
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
			
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) {
				cc[i] += dist[i][j];
			}
			
			int min = INF; for (int i = 0; i < N; i++) min = Math.min(min, cc[i]);

			bw.write("#"+tc+" "+min+"\n");
		}
		
		bw.flush();bw.close();
	}
}
