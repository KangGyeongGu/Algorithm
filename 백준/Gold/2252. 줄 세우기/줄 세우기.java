import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static int[] inDegree;
	static List<List<Integer>> dag = new ArrayList<>();
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException { 
		init();
		topologySort();
		System.out.println(sb);
	}
	
	private static void topologySort() {
		while (!Q.isEmpty()) {
			int vertex = Q.poll();
			sb.append(vertex + " ");
			inDegree[vertex] = -1;
			
			for (int edge : dag.get(vertex)) {
				inDegree[edge]--;
				if (inDegree[edge]==0) {
					if (dag.get(edge).isEmpty()) {
						sb.append(edge + " ");
						inDegree[edge] = -1;
						continue;
					}
					Q.offer(edge); 
					inDegree[edge] = -1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				sb.append(i + " ");
			}
		}
		
		sb.append('\n');
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N+1; i++) {
			dag.add(new ArrayList<>());
		}
		
		inDegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			dag.get(u).add(v);
			inDegree[v]++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i]==0) {
				Q.offer(i);
			}
		}
	}
}
