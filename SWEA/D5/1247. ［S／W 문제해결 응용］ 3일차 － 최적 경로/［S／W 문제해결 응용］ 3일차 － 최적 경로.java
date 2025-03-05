import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node {
		int x; int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, minCost;
	static Node[] C; 
	static boolean[] iv; 
	static Node stx, etx;
	
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			simulation();
		}
		System.out.println(sb);
	}
	
	// simulate each test cases
	private static void simulation() {
		dfs(0, stx, 0);
		sb.append(minCost).append('\n');
	}
	
	// dfs + backtrack + pruning
	private static void dfs(int depth, Node prev, int cost) {
		if (cost > minCost) return;
		
		if (depth == N) {
			cost += calcDist(prev.x, prev.y, etx.x, etx.y);
			minCost = Math.min(minCost, cost);
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (iv[i]) continue;
			iv[i] = true;
			dfs(depth+1, C[i], cost+calcDist(prev.x, prev.y, C[i].x, C[i].y));
			iv[i] = false; 
		}
	}
	
	// initialize each test cases,
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());

		iv = new boolean[N+1];
		C = new Node[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		stx = new Node(x, y); // Company Coordinates,
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		etx = new Node(x, y); // Home Coordinates,
		
		for (int i = 1; i <= N; i++) { // Customers Coordinates,
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			C[i] = new Node(x, y);
		}
		
		minCost = Integer.MAX_VALUE;
	}
	
	// Calculate Distance between two Nodes
	private static int calcDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}