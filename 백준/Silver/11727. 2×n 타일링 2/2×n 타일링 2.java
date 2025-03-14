import java.io.*;

public class Main {

	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		
//		System.out.println(dp(N));
		System.out.println(bottomUp(N));
		
	}
	
	private static int topDown(int n) {
		if (n == 1) return 1;
		if (n == 2) return 3;
		if (dp[n]!=0) return dp[n];
		
		return dp[n] = (topDown(n-1) + 2 * topDown(n-2)) % 10007;
	}
	
	private static int bottomUp(int n) {
		dp[1] = 1;
		
		if (N >= 2) dp[2] = 3;
		
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
		}
		
		return dp[N];
	}
}
