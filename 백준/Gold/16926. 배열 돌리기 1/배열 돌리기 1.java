import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DIR = { {0,1}, {1,0}, {0,-1}, {-1,0} };
	static int N, M, R, layer;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		init();
		run();
		print();
	}
	
	private static void run() {
		for (int i = 0; i < R; i++) rotate();
	}
	
	/* In order to push elements according to the direction of rotation,
	 * we must know the previous element based on the current element.
	 * for example, if the current element's index is [i][j] and if we push this element to the right side,
	 * we must know the element where the index is [i][j+1], not [i][j-1]. 
	 * */
	private static void rotate() {
		for (int l = 0; l < layer; l++) {
			int x = l; int y = l;
			int tmp = arr[x][y];
			int idx = 0;
			
			while (idx < 4) {
				int bx = x + DIR[idx][0];
				int by = y + DIR[idx][1];
				
				if (!isValidRange(l, bx, by)) {
					idx++;
					continue;
				}
				
				arr[x][y] = arr[bx][by];
				x = bx;
				y = by;
			}
			
			arr[l+1][l] = tmp;
		}
	}
	
	private static boolean isValidRange(int l, int bx, int by) {
		return l <= bx && bx < N-l && l <= by && by < M-l; 
	}
	
	private static void print() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		layer = Math.min(N, M) / 2;
		arr = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken()); 
			}
		}
		br.close();
	}
}
