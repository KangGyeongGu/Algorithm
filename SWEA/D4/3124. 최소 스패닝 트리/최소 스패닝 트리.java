import java.util.*;
import java.io.*;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int u, v, w;

		public Edge(int u, int v, int w) {
			super(); this.u = u; this.v = v; this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int T, V, E;
	static int[] sets;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			kruskal(tc);
		}
		System.out.println(sb);
	}
	
	private static void kruskal(int tc) {
		long ans = 0;
        int cnt = 0;
		
		while (!pq.isEmpty() && cnt < V-1) {
			Edge curE = pq.poll();
			
			if(!union(curE.u, curE.v)) continue;
			
			ans += curE.w;
			cnt++;
		}
		
		sb.append("#" + tc + " " + ans + '\n');
	}
	
	private static int find(int x) {
		if (sets[x] < 0) return x;
		return sets[x] = find(sets[x]);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return false;
		if (sets[a]==sets[b]) sets[a]--;
		if(sets[a]<sets[b]) sets[b] = a;
		else sets[a] = b;
		return true;
	}
	
	private static void init(int tc) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		pq.clear();
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(u, v, w));
		}
		
		sets = new int[V+1];
		Arrays.fill(sets, -1);
	}
}
