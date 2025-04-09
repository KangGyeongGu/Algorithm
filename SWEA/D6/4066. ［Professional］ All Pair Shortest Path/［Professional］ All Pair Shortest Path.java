import java.io.*;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int INF = 1_000_000_000, T = read();
        
		for (int tc = 1; tc <= T; tc++) {
			int N = read(), M = read(), dist[][] = new int[N][N];
			
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) dist[i][j] = (i==j) ? 0 : INF;
			
			for (int i = 0; i < M; i++) {
				int u = read(), v = read(), w = read();
				if (dist[u-1][v-1] > w) dist[u-1][v-1] = w;
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j]; 
					}
				}
			}
			
			bw.write("#" + tc + " ");
			for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) bw.write(dist[i][j] == INF ? "-1 " : dist[i][j] + " ");
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}
