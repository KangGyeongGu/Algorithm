import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] adj;
	static int N;

	private static int read() throws Exception {
		int c = System.in.read(), n = 0, sign = 1;
		while (c <= 32) c = System.in.read();
		if (c == '-') { sign = -1; c = System.in.read(); }
		n = c & 15; while ((c = System.in.read()) >= '0') n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read(); return n * sign;
	}

	public static void main(String[] args) throws Exception {
		int T = read();
		
		for (int tc = 1; tc <= T; tc++) {
			N = read();
			adj = new int[N][N];
			
			// 인접행렬 입력
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					adj[i][j] = read();

			int min = Integer.MAX_VALUE;

			// 각 노드에서 BFS 수행
			for (int i = 0; i < N; i++) {
				int sum = bfs(i);
				min = Math.min(min, sum);
			}

			bw.write("#" + tc + " " + min + "\n");
		}
		bw.flush(); bw.close();
	}

	// BFS 수행: 시작 노드로부터 각 노드까지 거리 합 리턴
	private static int bfs(int start) {
		boolean[] vis = new boolean[N];
		int[] dist = new int[N];
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		vis[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next = 0; next < N; next++) {
				if (!vis[next] && adj[cur][next] == 1) {
					vis[next] = true;
					dist[next] = dist[cur] + 1;
					q.offer(next);
				}
			}
		}

		int sum = 0;
		for (int d : dist) sum += d;
		return sum;
	}
}
