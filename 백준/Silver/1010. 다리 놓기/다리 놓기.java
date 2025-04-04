import java.io.*;

public class Main {
	
	static int T, N, M, dp[][] = new int[30][30];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read();
		return n;
	}
	
	private static int dp(int m, int n) {
		if (dp[m][n] > 0) return dp[m][n];
		if (m==n || n==0) return dp[m][n] = 1;
		return dp[m][n] = dp(m-1, n-1) + dp(m-1, n);
	}
	
	public static void main(String[] args) throws Exception {
		T = read();
		while (T-->0) {
			N = read();
			M = read();
			bw.write(dp(M, N) + "\n");
		}
		bw.flush(); bw.close();
	}
}