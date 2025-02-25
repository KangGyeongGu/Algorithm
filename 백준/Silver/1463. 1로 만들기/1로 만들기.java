import java.io.*;

public class Main {
	
	static int N;
	static Integer[] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dp(N));
	}
	
	static int dp(int N) {
		if (dp[N] == null) {
			if (N%6==0) {
				dp[N] = Math.min(dp(N-1), Math.min(dp(N/2), dp(N/3))) + 1;
			}
			else if (N%3==0) {
				dp[N] = Math.min(dp(N/3), dp(N-1)) + 1; 
			}
			else if (N%2==0) {
				dp[N] = Math.min(dp(N/2), dp(N-1)) + 1;
			}
			else {
				dp[N] = dp(N-1) + 1;
			}
		}
		return dp[N];
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new Integer[N+1]; dp[0] = dp[1] = 0;
		br.close();
	}
}

