import java.util.*;
import java.io.*;

public class Main {

	static class Node {
		int x;
		int y;
		int step;
		int destroy;
		boolean isDay;
		
		Node (int x, int y, int step, int destroy, boolean isDay) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.destroy = destroy;
			this.isDay = isDay;
		}
	}
	
	static int N, M, K;
	static int[][] map;
	static boolean[][][] iv;
	static Queue<Node> Q;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}
	
	private static int bfs() {
		
		while (!Q.isEmpty()) {
			
			Node cur = Q.poll();
			
			if (cur.x == N-1 && cur.y == M-1) return cur.step;
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (!isValid(nx, ny)) continue;
				
				if (isValidMoveWithoutDestroy(nx, ny, cur.destroy)) {
					iv[nx][ny][cur.destroy] = true;
					Q.offer(new Node(nx, ny, cur.step+1, cur.destroy, !cur.isDay));
				}
				
				if (isValidMoveWithDestroy(nx, ny, cur.destroy)) {
					if (cur.isDay) {
						iv[nx][ny][cur.destroy+1] = true;
						Q.offer(new Node(nx, ny, cur.step+1, cur.destroy+1, !cur.isDay));
					}
					else {
						Q.offer(new Node(cur.x, cur.y, cur.step+1, cur.destroy, !cur.isDay));
					}
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}
	
	private static boolean isValidMoveWithoutDestroy(int nx, int ny, int destroy) {
		return map[nx][ny] == 0 && !iv[nx][ny][destroy];
	}
	
	private static boolean isValidMoveWithDestroy(int nx, int ny, int destroy) {
		return map[nx][ny] == 1 && destroy < K && !iv[nx][ny][destroy+1];
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = in.charAt(j) - '0';
			}
		}
		
		iv = new boolean[N][M][K+1];
		iv[0][0][0] = true;
		Q = new ArrayDeque<>();
		Q.offer(new Node(0, 0, 1, 0, true));
	}
}