import java.util.*;
import java.io.*;

public class Solution {

	static int T = 10;
	static int N, start, max;
	static boolean[] iv;
	static List<Integer> result;
	static List<Set<Integer>> adj;
	static Queue<Integer> Q;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			bfs(start);
			sb.append(result.get(result.size()-2)).append('\n');
		}
		System.out.println(sb);
	}
	
	
	private static void bfs(int startNode) {
		Q = new ArrayDeque<>();
		Q.offer(startNode);
		iv[startNode] = true;
		result = new ArrayList<>();
		
		while (!Q.isEmpty()) {
			int qSize = Q.size();
			int max = Integer.MIN_VALUE;
			
			while (qSize-- > 0) {
				int cur = Q.poll();
				
				for (int next : adj.get(cur)) {
					if (iv[next]) continue;
					iv[next] = true;
					max = Math.max(max, next);
					Q.offer(next);
				}
			}
			result.add(max);
		}
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#" + tc + " ");
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		iv = new boolean[101];
		adj = new ArrayList<>();
		for (int i = 0; i <= 100; i++) {
			adj.add(new HashSet<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N/2; i++) {
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj.get(u).add(v);
		}
	}
}