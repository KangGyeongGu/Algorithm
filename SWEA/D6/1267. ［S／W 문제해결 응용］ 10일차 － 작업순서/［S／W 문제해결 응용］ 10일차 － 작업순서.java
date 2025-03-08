import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T = 1;
	static int V, E;
	
	static int[] inDegree;
	static List<List<Integer>> dag;
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	private static void run() throws IOException {
		while (T <= 10) {
			init(T++);
			topologySort();
		}
		System.out.println(sb);
	}
	
	/* 
	 * There's condition that the input is clearly DAG,
	 * so we don't need to check if the graph is DAG.
	 * */
	private static void topologySort() {
		while (!Q.isEmpty()) {
			int node = Q.poll();
			sb.append(node + " "); // directly save this node,
			
			for (int edge : dag.get(node)) {
				if (--inDegree[edge]==0) { // (1) reduce the count of indegree by one and check if this Vertex is now has no indgree.
					Q.offer(edge); // (2) if it is, add this to the Queue.
				}
			}
		}
		sb.append('\n');
	}
	
	/* test case initialization */
	private static void init(int tc) throws IOException {
		sb.append("#" + tc + " ");
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		inDegree = new int[V+1]; // array for count indegrees of each Vertex,
		dag = new ArrayList<>(); // directed acyclic graph as adjacency list
		for (int v = 0; v <= V; v++) { 
			dag.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int e = 0; e < E; e++) {
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			dag.get(u).add(v); // note Edge info
			inDegree[v]++;	// count current Vertex's indegree
		}
		
		for (int v = 1; v <= V; v++) { // find Vertexes that has no indegree
			if (inDegree[v]==0) {
				Q.offer(v);
			}
		}
	}
}