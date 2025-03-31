import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Core {
		int x, y; 
		
		Core (int x, int y) { 
			this.x = x; this.y = y; 
		}
	}
	
	static int T, N, minLength;
	static int[][] wafer;
	static boolean[] selectedCore;
	static List<Core> coreList;
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			for (int i = coreList.size(); i >= 0; i--) {
				combination(0, 0, i);
				if (minLength < Integer.MAX_VALUE) break;
			}
			sb.append("#" + tc + " " + minLength + '\n');
		}
		System.out.println(sb);
	}
	
	private static void combination(int depth, int start, int coreCount) {
		if (depth == coreCount) {
			dfs(0, 0);
			return;
		}
		
		for (int i = start; i < coreList.size(); i++) {
			selectedCore[i] = true;
			combination(depth+1, i+1, coreCount);
			selectedCore[i] = false;
		}
	}
	
	private static void dfs(int idx, int wireLength) {
		if (idx == coreList.size()) {
			minLength = Math.min(minLength, wireLength);
			return;
		}
		
		if (!selectedCore[idx]) {
			dfs(idx+1, wireLength);
			return;
		}
		
		for (int[] dir : DIR) {
			int x = coreList.get(idx).x;
			int y = coreList.get(idx).y;
			
			int ConnLength = 0;
			boolean isConn = false;
			
			while (true) {
				x += dir[0];
				y += dir[1];
				
				if (N <= x || x < 0 || N <= y || y < 0) {
					isConn = true;
					break;
				}
				
				if (wafer[x][y] != 0) break;
				
				wafer[x][y] = 2;
				ConnLength++;
			}
			
			if (isConn) dfs(idx+1, wireLength+ConnLength);
			
			while (true) {
				x -= dir[0];
				y -= dir[1];
				
				if (x == coreList.get(idx).x && y == coreList.get(idx).y) break;
				
				wafer[x][y] = 0;
			}
		}
	}
	
	private static void init(int tc) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		wafer = new int[N][N];
		coreList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				wafer[i][j] = Integer.parseInt(st.nextToken());
				if (wafer[i][j]==1) coreList.add(new Core(i, j));
			}
		}
		
		selectedCore = new boolean[coreList.size()];
		minLength = Integer.MAX_VALUE;
	}
}