import java.io.*;

public class Main {

	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		
		System.out.println(dp(N));
		
	}
	
	private static int dp(int n) {
		if (n == 1) return 1;
		if (n == 2) return 3;
		if (dp[n]!=0) return dp[n];
		
		return dp[n] = (dp(n-1) + 2 * dp(n-2)) % 10007;
	}
}
