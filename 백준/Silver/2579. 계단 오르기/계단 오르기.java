import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] steps;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
		System.out.println(dp[N]);
	}
	
	private static void dp() {
		if (N==1) return;
		
		dp[2] = steps[2] + steps[1];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = steps[i] + Math.max(dp[i-2], steps[i-1]+dp[i-3]); 
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		steps = new int[N+1];
		for (int i = 1; i <= N; i++) {
			steps[i] = Integer.parseInt(br.readLine());
		}
		dp[1] = steps[1];
	}
}
