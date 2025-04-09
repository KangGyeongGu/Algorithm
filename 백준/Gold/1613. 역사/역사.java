import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<1) + (n<<3) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int N = read(), K = read();
		int[][] dist = new int[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			int u = read(), v = read();

			dist[u][v] = -1;
			dist[v][u] = 1;
		}
		
		
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i==k) continue;
				for (int j = 1; j <= N; j++) {
					if (j==k || j==i) continue;
					if (dist[i][k] != 0 && dist[i][k] == dist[k][j]) dist[i][j] = dist[i][k];
				}
			}
		}
		
		int S = read();
		for (int i = 0; i < S; i++) {
			int u = read(), v = read();
			sb.append(dist[u][v]).append('\n');
		}
		System.out.println(sb);
	}
}
