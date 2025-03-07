import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, MAX;
	static int[][] map;
	static boolean[] types;

	static final int[][] DIR = { {1,1}, {1,-1}, {-1,-1}, {-1,1} };
	static final int INF = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	private static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			routing();
		}
		System.out.println(sb);
	}
	
	private static void routing() {
		for (int i = 0; i < N-2; i++) {
			for (int j = 1; j < N-1; j++) {
				types = new boolean[101];
				types[map[i][j]] = true;
				dfs(1, i, j, i, j, 0);
			}
		}
		
		if (MAX == INF) sb.append(-1).append('\n');
		else sb.append(MAX).append('\n');
	}
	
	private static void dfs(int count, int cx, int cy, int ix, int iy, int prevDir) {
		
		for (int i = prevDir; i < 4; i++) {
			int nx = cx + DIR[i][0];
			int ny = cy + DIR[i][1];
			
			if (!isValid(nx, ny)) continue;
			
			if (nx==ix && ny==iy && prevDir >= 2) {
				MAX = Math.max(MAX, count);
				return;
			}
				
			if (!types[map[nx][ny]]) {
				types[map[nx][ny]] = true;
				dfs(count+1, nx, ny, ix, iy, i);
				types[map[nx][ny]] = false;
			}
		}
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0<=nx && nx<N && 0<=ny && ny<N; 
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		MAX = INF;
	}
}
