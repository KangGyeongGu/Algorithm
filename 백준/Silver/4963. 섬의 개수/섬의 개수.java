import java.io.*;
import java.util.*;

public class Main {
	public static final int LAND = 1, SEA = 0;
	public static final int[][] DIR = { 
			{1,0}, {-1,0}, {0,1}, {0,-1},
			{-1,-1}, {-1,1}, {1,-1}, {1,1}
	}; 

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int W, H, count;
	static int[][] map;
	static boolean[][] iV;
	
	static Stack<int[]> adj = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		while (true) {
			if (init()) break;
			run();
		}
		System.out.println(sb);
	}
	
	private static void run() {
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (!iV[h][w] && map[h][w]==1) {
					iV[h][w] = true;
					dfs(h, w);
					count++;
				}
			}
		}
		sb.append(count).append('\n');
	}
	
	private static void dfs(int x, int y) {
		adj.push(new int[] {x, y});
		
		while(!adj.isEmpty()) {
			int[] curP = adj.pop();
			int cr = curP[0]; int cc = curP[1];
			
			for (int[] d : DIR) {
				int nr = cr + d[0];
				int nc = cc + d[1];
				
				if (!isValidRange(nr, nc)) continue;
				
				iV[nr][nc] = true;
				adj.push(new int[] {nr, nc});
			}
		}
	}
	
	private static boolean isValidRange(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W && !iV[nr][nc] && map[nr][nc] != 0;
	}
	
	
	private static boolean init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		if (W==0 && H==0) return true;
		
		map = new int[H][W];
		iV = new boolean[H][W];
		count = 0;
		
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			
			for (int w = 0; w < W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		return false;
	}
}