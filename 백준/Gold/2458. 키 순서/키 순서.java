import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, ANS;
	static boolean[] iv;
	static List<List<Integer>> adj, radj;
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read();
		return n;
	}
	
	private static void init() throws Exception {
		N = read();
		M = read();
		
		adj = new ArrayList<>();
		radj = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
			radj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			adj.get(a).add(b);
			radj.get(b).add(a);
		}
	}
	
	private static int dfs(int node, List<List<Integer>> graph) {
		iv[node] = true;
		int count = 0;
		
		for (int next : graph.get(node)) {
			if (iv[next]) continue;
			
			count += 1 + dfs(next, graph);
		}
		
		return count;
	}
	
	private static void run() {
		for (int i = 1; i <= N; i++) {
			iv = new boolean[N+1];
			int taller = dfs(i, adj);
			
			iv = new boolean[N+1];
			int smaller = dfs(i, radj);
			
			if (taller + smaller == N-1) ANS++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		run();
		System.out.println(ANS);
	}
}
