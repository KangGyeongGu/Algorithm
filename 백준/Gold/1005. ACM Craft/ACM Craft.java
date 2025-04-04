import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while (( c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c==13) System.in.read(); 
		return n;
	}
	
	static int T, N, K, W;
	static int[] buildTime, inDegrees, result;
	static List<List<Integer>> adj;
	
	private static void init() throws Exception {
		N = read();
		K = read();
		
		buildTime = new int[N + 1];
		result = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			buildTime[i] = read();
			result[i] = buildTime[i];
		}
		
		adj = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		inDegrees = new int[N + 1];
		for (int i = 0; i < K; i++) {
			int u = read();
			int v = read();
			adj.get(u).add(v);
			inDegrees[v]++;
		}
		
		W = read();
	}
	
	private static int topologicalSort() {
		Queue<Integer> Q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegrees[i] == 0) Q.offer(i);
		}
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			for (int next : adj.get(cur)) {
				result[next] = Math.max(result[next], result[cur] + buildTime[next]);
				if (--inDegrees[next] == 0) Q.offer(next);
			}
		}
		
		return result[W];
	}
	
	public static void main(String[] args) throws Exception {
		T = read();
		while (T-- > 0) {
			init();
			bw.write(topologicalSort() + "\n");
		}
		bw.flush(); bw.close();
	}
}
