import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Shortcut {
		int u, v, w;

		public Shortcut(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	
	static int N, D, dp[];
	static List<Shortcut> shortcuts;
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		shortcuts = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if (v > D || v - u <= w) continue;
			
			shortcuts.add(new Shortcut(u, v, w));
		}
	}
	
	private static void dp() {
		dp = new int[D+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int d = 0; d <= D; d++) {
			if (d > 0) dp[d] = Math.min(dp[d], dp[d-1]+1);
			
			for (Shortcut shortcut : shortcuts) {
				if (shortcut.u == d) {
					if (shortcut.v <= D) {
						dp[shortcut.v] = Math.min(dp[shortcut.v], dp[d] + shortcut.w);
					}
				}
			}
		}
		
		System.out.println(dp[D]);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		dp();
	}
}