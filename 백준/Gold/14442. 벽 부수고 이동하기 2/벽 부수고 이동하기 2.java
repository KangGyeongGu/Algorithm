import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, K;
	static int[][] map;
	static boolean[][][] iv;
	static Queue<int[]> Q;
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}
	
	private static int bfs() {
		while (!Q.isEmpty()) {
			
			int[] cur = Q.poll();
			int cx = cur[0], cy = cur[1], step = cur[2], destroy = cur[3];
			
			if (cx == N-1 && cy == M-1) return step;
			
			for (int[] dir : DIR) {
				int nx = cx + dir[0], ny = cy + dir[1];
				
				if (!isValid(nx, ny)) continue;
				
				if (isValidMoveWithoutWall(nx, ny, destroy)) {
					iv[nx][ny][destroy] = true;
					Q.offer(new int[] {nx, ny, step+1, destroy});
				}
				
				if (isValidMoveThroughWall(nx, ny, destroy)) {
					iv[nx][ny][destroy+1] = true;
					Q.offer(new int[] {nx, ny, step+1, destroy+1});
				}
			}
		}
		return -1;
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}
	
	private static boolean isValidMoveWithoutWall(int nx, int ny, int destroy) {
		return map[nx][ny] == 0 && !iv[nx][ny][destroy];
	}
	
	private static boolean isValidMoveThroughWall(int nx, int ny, int destroy) {
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
		Q = new ArrayDeque<>();
		Q.offer(new int[] {0, 0, 1, 0});
		iv[0][0][0] = true;
	}
}
