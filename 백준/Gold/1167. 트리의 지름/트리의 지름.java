import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Edge {
		int v, dist;
		Edge (int v, int dist) { this.v = v; this.dist = dist; }
	}
	
	static int V, maxDist, farthestNode;
	static boolean[] iv;
	static List<List<Edge>> adj = new ArrayList<>();
	
	private static void init() throws Exception {
		V = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
		
		for (int i = 1; i <= V; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			
			while (true) {
				int v = Integer.parseInt(st.nextToken());
				
				if (v == -1) break;
				
				int dist = Integer.parseInt(st.nextToken());
				
				adj.get(u).add(new Edge(v, dist));
			}
		}
	}
	
	private static void dfs(int node, int sum) {
		iv[node] = true;
		
		if (sum > maxDist) {
			maxDist = sum;
			farthestNode = node;
		}
		
		for (Edge edge : adj.get(node)) {
			if (iv[edge.v]) continue;
			dfs(edge.v, sum + edge.dist);
		}
	}
	
	private static void findDiameter() {
		iv = new boolean[V+1];
		dfs(1, 0);
		
		iv = new boolean[V+1];
		dfs(farthestNode, 0);
		
		System.out.println(maxDist);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		findDiameter();
	}
}
