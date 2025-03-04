import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, drawNum, maxSize;
	static int[][] draw;
	static boolean[][] iv;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		iv = new boolean[N][M];
		draw = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				draw[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxSize = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (draw[i][j]!=0 && !iv[i][j]) {
					iv[i][j] = true; 
					maxSize = Math.max(maxSize, bfs(i, j));
					drawNum++;
				}
			}
		}
		if (maxSize == Integer.MIN_VALUE) System.out.println(0 + "\n" + 0);
		else System.out.println(drawNum + "\n" + maxSize);
	}
	
	private static int bfs(int x, int y) {
		Queue<int[]> Q = new ArrayDeque<>();
		Q.offer(new int[] {x,y});
		
		int cnt = 1;
		
		while (!Q.isEmpty()) {
			int[] curPos = Q.poll();
			int cx = curPos[0]; int cy = curPos[1];
			
			for (int[] dir : DIR) {
				int nx = cx + dir[0]; int ny = cy + dir[1];
				
				if (!(0<=nx && nx < N && 0<=ny && ny < M && !iv[nx][ny] && draw[nx][ny]!=0)) continue;
				
				iv[nx][ny] = true; cnt++;
				Q.offer(new int[] {nx, ny});
			}
		}
		
		return cnt;
	}
	
	
}
