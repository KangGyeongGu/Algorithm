import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, pC, minCost;
	static int[] price, plan;
	static boolean[] iv;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	private static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			dfs(0, 0);
			sb.append(minCost).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void dfs(int depth, int cost) {
		if (depth >= 12) { // basis condition
			minCost = Math.min(minCost, cost);
			return;
		}
		
		if (cost > minCost) return; // prunning

		if (plan[depth] == 0) { // prunning
			dfs(depth+1, cost);
			return;
		}
		
		dfs(depth+1, cost + plan[depth]*price[0]); // 1day
		dfs(depth+1, cost + price[1]); // 1 month
		dfs(depth+3, cost + price[2]); // 3 months
		dfs(12, cost + price[3]); // year
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		price = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) price[i] = Integer.parseInt(st.nextToken());
		
		pC = 0;  plan = new int[12]; iv = new boolean[12];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 12; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
			if (plan[i]!=0) pC++;
		}
		
		minCost = Integer.MAX_VALUE;
	}
}
