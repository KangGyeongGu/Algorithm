import java.io.*;
import java.util.*;

public class Main {
	
	static int N, max, min;
	static int[][][] map;
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
	}

	private static void dp() {
		for (int i = 1; i < N; i++) {
			map[0][i][0] += Math.max(map[0][i-1][0], map[0][i-1][1]);
			map[0][i][1] += Math.max(Math.max(map[0][i-1][0], map[0][i-1][1]), map[0][i-1][2]);
			map[0][i][2] += Math.max(map[0][i-1][1], map[0][i-1][2]);
			
			map[1][i][0] += Math.min(map[1][i-1][0], map[1][i-1][1]);
			map[1][i][1] += Math.min(Math.min(map[1][i-1][0], map[1][i-1][1]), map[1][i-1][2]);
			map[1][i][2] += Math.min(map[1][i-1][1], map[1][i-1][2]);
		}
		
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, map[0][N-1][i]);
			min = Math.min(min, map[1][N-1][i]);
		}
		
		System.out.println(max + " " + min);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		map = new int[2][N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int in = Integer.parseInt(st.nextToken());
				map[0][i][j] = in;
				map[1][i][j] = in;
			}
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
	}
}