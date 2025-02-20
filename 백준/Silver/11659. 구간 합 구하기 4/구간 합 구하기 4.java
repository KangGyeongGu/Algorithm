import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static long[] A;
	static int[][] IJ;
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}

	static void run() {
		for (int i = 0; i < M; i++) {
			int stx = IJ[i][0]-1; int etx = IJ[i][1];
			prefixSum(stx, etx);
		}
		System.out.println(sb);
	}
	
	static void prefixSum(int stx, int etx) {
		long sum = A[etx] - A[stx];
		sb.append(sum).append('\n');
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) { // initialize array using prefix sum
			int in = Integer.parseInt(st.nextToken());
			A[i] = A[i-1] + in; 
		}
		
		IJ = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			IJ[i][0] = Integer.parseInt(st.nextToken());
			IJ[i][1] = Integer.parseInt(st.nextToken());
		}
	}
}
