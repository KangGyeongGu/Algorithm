import java.util.*;
import java.io.*;

public class Main {

	static class Node implements Comparable<Node> {
		int v, w;
		Node (int v, int w) { this.v = v; this.w = w; }
		@Override public int compareTo(Node o) { return Integer.compare(this.w, o.w); }
	}
	
	static int N, E, v1, v2, dist[];
	static List<List<Node>> adj = new ArrayList<>();
	static PriorityQueue<Node> pQ;
	static final int INF = 200_000_000;
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int n = 0; n <= N; n++) adj.add(new ArrayList<>());
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj.get(u).add(new Node(v, w));
			adj.get(v).add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
	}
	
	static int dijkstra(int stx, int etx) {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		pQ = new PriorityQueue<>();
		pQ.offer(new Node(stx, 0));
		dist[stx] = 0;
		
		while (!pQ.isEmpty()) {
			Node cur = pQ.poll();
			
			if (dist[cur.v] < cur.w) continue;
			
			for (Node next : adj.get(cur.v)) {
				if (dist[next.v] > next.w + cur.w) {
					dist[next.v] = next.w + cur.w;
					pQ.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		return dist[etx];
	}
	
	static int route() {
		int r11 = dijkstra(1, v1);
		int r12 = dijkstra(v1, v2);
		int r13 = dijkstra(v2, N);
		
		int r21 = dijkstra(1, v2);
		int r22 = dijkstra(v2, v1);
		int r23 = dijkstra(v1, N);
		
		int ANS = Math.min(r11+r12+r13, r21+r22+r23);
		
		if (ANS >= INF) return -1;
		return ANS;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(route());
	}
}
