import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	static int[][] knights = { 
			{-1,-2}, {-2,-1},
			{-1, 2}, {-2, 1},
			{1, -2}, {2, -1},
			{1, 2}, {2, 1},
	};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
	
		int[][] cB = new int[H][W];
		boolean[][][] iV = new boolean[K+1][H][W];
		
		
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int w = 0; w < W; w++) {
				cB[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		
		// bfs
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, K, 0}); // x, y, Knight move count, total move count
		iV[K][0][0] = true;
		
		while (!q.isEmpty()) {
			int[] node = q.poll();
			
			int r = node[0]; int c = node[1];
			int k = node[2]; int mC = node[3];
			
			if (r == H-1 && c == W-1) {
				System.out.println(mC);
				return;
			}
			
			// basic direction
			for (int i = 0; i < 4; i++) {
				int nR = r + deltas[i][0];
				int nC = c + deltas[i][1];

				if (nR < 0 || nR >= H || nC < 0 || nC >= W) continue;
				
				if (cB[nR][nC] != 1 && !iV[k][nR][nC]) {
					iV[k][nR][nC] = true; 
					q.offer(new int[] {nR, nC, k, mC + 1});
				}
			}
			
			// knight direction
			if (k > 0) {
				for (int i = 0; i < 8; i++) {
					int nR = r + knights[i][0];
					int nC = c + knights[i][1];
					
					if (nR < 0 || nR >= H || nC < 0 || nC >= W) continue;
					
					if (cB[nR][nC] != 1 && !iV[k-1][nR][nC]) {
						iV[k-1][nR][nC] = true;
						q.offer(new int[] {nR, nC, k - 1, mC + 1});
					}
				}
			}
		}
		// search fail
		System.out.println(-1);
	}
}