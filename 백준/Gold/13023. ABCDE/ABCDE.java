import java.util.*;
import java.io.*;

public class Main {

	static int T, N, M;
	static boolean isConn;
	static boolean[] iv;
	static List<List<Integer>> adj = new ArrayList<>();
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int i = 0; i < N; i++) {
			iv = new boolean[N];
			dfs(1, i);
			if (isConn) break;
		}
		
		if (isConn) System.out.println(1);
		else System.out.println(0);
	}
	
	
	private static void dfs(int depth, int node) {
		
		if (depth == 5) {
			isConn = true;
			return;
		}
		
		iv[node] = true;
		
		for (int next : adj.get(node)) {
			if (iv[next]) continue;
			
			dfs(depth+1, next);
			
			if (isConn) return;
		}
		
		iv[node] = false;
	}
	
	private static void init() throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}
		
		while (M-->0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
	}
}
