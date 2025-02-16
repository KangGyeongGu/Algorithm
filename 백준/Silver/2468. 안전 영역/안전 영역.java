import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} }; 
	static int N, min, max, tMax, count;
	static int[][] map, tmp;
	static boolean[][] iV;
	static Stack<int[]> stack;
	
	public static void main(String[] args) throws IOException {
		init();
		runAll();
		System.out.println(tMax);
	}
	
	private static void runAll() {
		for (int tc = 0; tc <= 100; tc++) {
			initEachCase(tc);
			runEachCase();
			tMax = Math.max(count, tMax);
		}
	}
	
	private static void initEachCase(int tc) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = map[r][c] - tc;  
			}
		}
		iV = new boolean[N][N];
		count = 0;
	}
	
	private static void runEachCase() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (tmp[r][c] > 0 && !iV[r][c]) {
					iV[r][c] = true;
					dfs(r, c);
					count++;
				}
			}
		}
	}
	
	private static void dfs(int r, int c) {
		stack = new Stack<>();
		stack.push(new int[] {r, c});
		
		while (!stack.isEmpty()) {
			int[] curP = stack.pop();
			int cr = curP[0]; int cy = curP[1];
			
			for (int[] dir : DIR) {
				int nr = cr + dir[0]; int nc = cy + dir[1];
				
				if (!isValidCoords(nr, nc)) continue;
				
				iV[nr][nc] = true;
				stack.push(new int[] {nr, nc});
			}
		}
	}
	
	private static boolean isValidCoords(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N && !iV[nr][nc] && tmp[nr][nc] > 0;
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		tmp = new int[N][N];
		iV = new boolean[N][N];
		
		min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int in = Integer.parseInt(st.nextToken());
				map[r][c] = in; 
				min = Math.min(min, in);
				max = Math.max(max, in);
			}
		}
	}
}