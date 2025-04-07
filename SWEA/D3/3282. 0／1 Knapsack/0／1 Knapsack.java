import java.io.*;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read(); 
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int T = read();
		for (int tc = 1; tc <= T; tc++) {
			int N = read(), K = read(), V[] = new int[N+1], C[] = new int[N+1], dp[] = new int[K+1];
			
			for (int i = 1; i <= N; i++) {
				V[i] = read();
				C[i] = read();
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = K; j-V[i] >= 0; j--) {
					dp[j] = Math.max(dp[j], dp[j-V[i]]+C[i]);
				}
			}
			bw.write("#" + tc + " " + dp[K] + "\n");
		}
		bw.flush(); bw.close();
	}
}
	