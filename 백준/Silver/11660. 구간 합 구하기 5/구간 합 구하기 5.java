import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static long[][] A;
	static int[][] IJ;
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	static void run() {
		for (int i = 0; i < M; i++) {
			prefixSum(IJ[i][0], IJ[i][1], IJ[i][2], IJ[i][3]);
		}
		System.out.println(sb);
	}
	
	static void prefixSum(int r1, int c1, int r2, int c2) {
		long sum = A[r2][c2] - A[r2][c1-1] - A[r1-1][c2] + A[r1-1][c1-1];
		sb.append(sum).append('\n');
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new long[N+1][N+1];
		for (int r = 1; r <= N; r++) { // initialize prefix sum 2D array
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				
				A[r][c] += (r > 1 ? A[r-1][c] : 0)
						+ (c > 1 ? A[r][c-1] : 0)
						- (r > 1 && c > 1 ? A[r-1][c-1] : 0);
			}
		}
		
		IJ = new int[M][4];
		for (int i = 0; i < M; i++) { 
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				IJ[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
	}
}
