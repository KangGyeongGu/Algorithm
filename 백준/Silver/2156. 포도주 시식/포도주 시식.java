import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read(); 
		return n;
	}

	/*
	 * i : 포도주 잔 순서
	 * D[i] : i 번째 포도주까지의 경우의 수의 최적해
	 * dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wines[i], dp[i-3] + wines[i-1] + wines[i]));
	 * */
	
	public static void main(String[] args) throws Exception {
		int N = read();
		int[] wines = new int[N+1];
		for (int i = 1; i <= N; i++) wines[i] = read();
		
		int[] dp = new int[N+1];
		
		dp[1] = wines[1];
		if (N > 1) dp[2] = wines[1] + wines[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wines[i], dp[i-3] + wines[i-1] + wines[i]));
		}

		bw.write(dp[N] + "\n");
		bw.flush(); bw.close();
	}
}