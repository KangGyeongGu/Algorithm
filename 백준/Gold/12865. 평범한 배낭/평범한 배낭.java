import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, K;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(knapsack());
	}
	
	
	private static int knapsack() throws IOException {
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for (int j = K; j >= w; j--) {
				dp[j] = Math.max(dp[j], dp[j-w] + v);
			}
		}
		return dp[K];
	}
	
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1];
	}
}