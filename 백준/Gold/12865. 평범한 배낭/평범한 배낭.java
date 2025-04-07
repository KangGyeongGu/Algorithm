import java.io.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read(); 
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int N = read(), K = read();
		
		int[] W = new int[N+1], V = new int[N+1], dp = new int[K+1];
		
		for (int i = 1; i <= N; i++) {
			W[i] = read();
			V[i] = read();
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = K; j-W[i] >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j-W[i]] + V[i]);
			}
		}
		
		bw.write(dp[K] + "\n");
		bw.flush(); bw.close();
	}
}
