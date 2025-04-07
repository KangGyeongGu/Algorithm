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
	
	public static void main(String[] args) throws Exception {
		int N = read();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		int ANS = 0;
		
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
			ANS = Math.max(ANS, dp[i]);
		}
		
		bw.write(ANS + "\n");
		bw.flush(); bw.close();
	}
}
