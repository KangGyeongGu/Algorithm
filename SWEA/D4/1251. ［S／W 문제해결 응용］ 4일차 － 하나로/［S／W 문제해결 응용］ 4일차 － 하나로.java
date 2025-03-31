import java.util.*;
import java.io.*;

public class Solution {
	
	static class Island {
		int x, y, id;
		public Island(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int v;
		long w;
		
		public Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int T, N;
	static double RATE;
	static List<List<Edge>> graph;
	static boolean[] visited;
	static PriorityQueue<Edge> pq;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(prim()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static long prim() {
		long ans = 0;
		int cnt = 0;
		
		pq.offer(new Edge(0, 0)); 
		while (!pq.isEmpty() && cnt < N) {
			Edge cur = pq.poll();
			
			if (visited[cur.v]) continue;
			visited[cur.v] = true;
			ans += cur.w;
			cnt++;
			
			for (Edge next : graph.get(cur.v)) {
				if (!visited[next.v]) pq.offer(next);
			}
		}
		
		return Math.round(RATE * ans);
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		int[] xCoords = new int[N];
		int[] yCoords = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) xCoords[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) yCoords[i] = Integer.parseInt(st.nextToken());
		
		List<Island> islands = new ArrayList<>(N);
		for (int i = 0; i < N; i++) islands.add(new Island(xCoords[i], yCoords[i], i));
		
		RATE = Double.parseDouble(br.readLine());
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				long dist = calcDist(islands.get(i), islands.get(j));
				graph.get(i).add(new Edge(j, dist));
				graph.get(j).add(new Edge(i, dist));
			}
		}
		
		visited = new boolean[N];
		pq = new PriorityQueue<>();
	}
	
	private static long calcDist(Island a, Island b) {
		return (long) (Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
}
