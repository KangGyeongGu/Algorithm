import java.io.*;
import java.util.*;

public class Main {
	
	static int T, N, K, W;
	static int[] buildTime, inDegrees, result;
	static List<List<Integer>> adj;
	static Queue<Integer> Q;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			init();
			sb.append(bfs()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int bfs() {
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			for (int next : adj.get(cur)) {
				result[next] = Math.max(result[next], result[cur] + buildTime[next]);
				if (--inDegrees[next] == 0) {
					Q.offer(next);
				}
			}
		}
		
		return result[W];
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		buildTime = new int[N+1];
		result = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			buildTime[i] = Integer.parseInt(st.nextToken());
			result[i] = buildTime[i];
		}
		
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		inDegrees = new int[N+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			inDegrees[v]++;
		}
		
		W = Integer.parseInt(br.readLine());
		
		Q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegrees[i]==0) Q.offer(i);
		}

	}
}
