import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int fvC;
	static int thC;
	
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		fvC = N/5; thC = N/3; dp = new int[fvC+1][thC+1];
		
//		System.out.println(byDP());
		System.out.println(byMath());
		
		br.close();
	}
	
	// dp (Bottom-up, for-loop)
	private static int byDP() {
		// start from (fvC, thC)
		for (int r = dp.length-1; r >= 0; r--) {
			for (int c = dp[0].length-1; c >= 0; c--) {
				dp[r][c] = 5*r + 3*c;
				if (dp[r][c] == N) return r+c;
			}
		}
		return -1;
	}
	
	private static int byMath() {
		
		if (N==4 || N==7) return -1;
		
		else if (N%5==1 || N%5==3) return N/5 + 1;
		
		else if (N%5==2 || N%5==4) return N/5 + 2;
		
		else return N/5;
		
	}
}