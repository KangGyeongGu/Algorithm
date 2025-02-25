import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, minDiff;
	static int[][] synergy, dish;
	static boolean[] iv;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			run();
		}
		System.out.println(sb);
		br.close();
	}

	/* simulate each test case and save output */
	static void run() {
		subset(0, 0);
		sb.append(minDiff).append('\n');
	}
	
	/* simulate test case */
	static void subset(int count, int start) {
		if (count == N/2) {
			splitIngred();
			minDiff = Math.min(calcSynergy(), minDiff);
			return;
		}
		
		for (int i = start; i < N; i++) {
			iv[i] = true;
			subset(count+1, i+1);
			iv[i] = false;
		}
	}
	
	/* split each dishes ingredients subset */
	static void splitIngred() {
		int aIdx = 0, bIdx = 0;
		for (int i = 0; i < iv.length; i++) {
			if (iv[i]) dish[0][aIdx++] = i;
			else dish[1][bIdx++] = i;
		}
	}
	
	/* about each dishA-B set, calculate each dish's total synergy */
	static int calcSynergy() {
		int aSum = 0, bSum = 0;
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < N/2; j++) {
				int ingred1 = dish[0][i]; int ingred2 = dish[0][j];
				aSum += synergy[ingred1][ingred2];
				
				ingred1 = dish[1][i]; ingred2 = dish[1][j];
				bSum += synergy[ingred1][ingred2];
			}
		}
		return Math.abs(aSum-bSum);
	}
	
	/* Test Case Initializer */
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		for (int r = 0; r < synergy.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < synergy[0].length; c++) {
				synergy[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		iv = new boolean[N];
		dish = new int[2][N/2]; 
		minDiff = Integer.MAX_VALUE;
	}
}
