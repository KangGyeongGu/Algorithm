import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int R, C, MAX;
	static char[][] B;
	static boolean[] alpha = new boolean[26];
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(0, 0, 1);
		System.out.println(MAX);
	}
	
	private static void dfs(int x, int y, int count) {
		MAX = Math.max(MAX, count);
		
		for (int[] dir : DIR) {
			int nx = x + dir[0]; 
			int ny = y + dir[1];
			
			if (!isValid(nx, ny) || alpha[B[nx][ny]-65]) continue;
			
			alpha[B[nx][ny]-65] = true;
			dfs(nx, ny, count+1);
			alpha[B[nx][ny]-65] = false;
		}
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0<=nx && nx<R && 0<=ny && ny<C;
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		B = new char[R][C];
		for (int r = 0; r < R; r++) {
			String in = br.readLine();
			for (int c = 0; c < C; c++) {
				B[r][c] = in.charAt(c); 
			}
		}
		MAX = Integer.MIN_VALUE;
		alpha[B[0][0]-65] = true;
	}
}
