import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static long result;
	
	static final long[] WDFidx = {1_000_000_000_000_000L, 1_000_000_000_000L, 1_000_000_000L, 1_000_000L, 1_000L, 1L}; // each Win idx in input String,
	static final int[][] cases = { {0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {1,2}, {1,3}, {1,4}, {1,5}, {2,3}, {2,4}, {2,5}, {3,4}, {3,5}, {4,5} };
	
	public static void main(String[] args) throws IOException {
		run();
	}

	private static void run() throws IOException {
		for (int i = 0; i < 4; i++) {
			init();
			sb.append(backtrack(0, 0) ? 1 : 0).append(" ");
		}
		System.out.println(sb);
	}
	
	private static boolean backtrack(int n, long status) {
		
		if (n == 15) return status == result;
		
		if (status > result) return false;
		
		int g1 = cases[n][0]; int g2 = cases[n][1];
		
		long W = status + 100 * WDFidx[g1] + WDFidx[g2]; 
		long D = status + 10 * WDFidx[g1] + 10 * WDFidx[g2];
		long L = status + WDFidx[g1] + 100 * WDFidx[g2];
		
		return backtrack(n+1, W) || backtrack(n+1, D) || backtrack(n+1, L); 
	}
	

	private static void init() throws IOException {
		result = Long.parseLong(br.readLine().replaceAll(" ", ""));
	}
}

