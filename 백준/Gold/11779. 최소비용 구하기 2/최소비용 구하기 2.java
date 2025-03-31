import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int v, w;
		Edge (int v, int w) { this.v = v; this.w = w; }
		
		@Override
		public int compareTo(Main.Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, stx, etx;
	static int[] dist, prev;
	static List<List<Edge>> dij = new ArrayList<>();
	static PriorityQueue<Edge> pQ = new PriorityQueue<>();
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		prev = new int[N+1];
		Arrays.fill(prev, -1);
		
		for (int i = 0; i <= N; i++) {
			dij.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dij.get(u).add(new Edge(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		stx = Integer.parseInt(st.nextToken());
		etx = Integer.parseInt(st.nextToken());
	}
	
	private static void dijkstra() {
		pQ.offer(new Edge(stx, 0));
		dist[stx] = 0;
		
		while (!pQ.isEmpty()) {
			Edge cur = pQ.poll();
			
			if (dist[cur.v] < cur.w) continue;
			
			for (Edge next : dij.get(cur.v)) {
				
				if (cur.w + next.w < dist[next.v]) {
					dist[next.v] = cur.w + next.w;
					prev[next.v] = cur.v;
					pQ.offer(new Edge(next.v, dist[next.v]));
				}
			}
		}
	}
	
	private static void printPath() {
		List<Integer> path = new ArrayList<>();
		int cur = etx;
		
		while (cur != -1) {
			path.add(cur);
			cur = prev[cur];
		}
		
		Collections.reverse(path);
		
		sb.append(dist[etx]).append('\n');
		sb.append(path.size()).append('\n');
		for (int p : path) sb.append(p).append(" ");
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dijkstra();
		printPath();
	}
}
