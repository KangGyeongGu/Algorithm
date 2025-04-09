import java.io.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int N = read(), M = read(), dist[][] = new int[N+1][N+1], INF = 1_000_000_000; // INF shouldn't be MAX_VALUE because of overFlow,
		
		for (int i = 1; i <= N; i++) { // distance[][] initialize,
			for (int j = 1; j <= N; j++) {
				if (i == j) dist[i][j] = 0;
				else dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) { // read input,
			int u = read(), v = read(), w = read();
			dist[u][v] = Math.min(dist[u][v], w); // in condition said, "시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다."
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) { // if cost (i->j) is more expensive than the route through K,
						dist[i][j] = dist[i][k] + dist[k][j]; // update min cost,
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.write(dist[i][j] == INF ? "0 " : dist[i][j] + " ");
			} bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}