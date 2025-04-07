import java.io.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		
		int N = read(), M = read(), mb[] = new int[N], cost[] = new int[N], maxCost = 0;
		
		for (int i = 0; i < N; i++) {
			mb[i] = read();
		}
		for (int i = 0; i < N; i++) {
			cost[i] = read();
			maxCost += cost[i];
		}
		
		int[] dp = new int[maxCost+1];
		
		for (int i = 0; i < N; i++) {
			for (int j = maxCost; j >= cost[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-cost[i]] + mb[i]);
			}
		}
		
		int ANS = Integer.MAX_VALUE;
		for (int i = 0; i <= maxCost; i++) {
			if (dp[i] >= M) {
				ANS = i;
				break;
			}
		}
		
		bw.write(ANS + "\n");
		bw.flush(); bw.close();
	}
}
