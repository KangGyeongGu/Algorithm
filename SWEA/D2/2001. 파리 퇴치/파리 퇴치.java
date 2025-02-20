import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, M, max;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= 10; tc++) {
			init(tc);
			catchFly();
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static void catchFly() {
		for (int r = 0; r+M <= N; r++) {
			for (int c = 0; c+M <= N; c++) {
				updateMaxCatch(r, c);
			}
		}
		sb.append(max).append('\n');
	}
	
	static void updateMaxCatch(int r, int c) {
		int sum = 0;
		for (int i = r; i < r+M; i++) {
			for (int j = c; j < c+M; j++) {
				sum += map[i][j];
			}
		}
		max = Math.max(max, sum);
	}
	
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		max = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); 
			}
		}
	}
}
