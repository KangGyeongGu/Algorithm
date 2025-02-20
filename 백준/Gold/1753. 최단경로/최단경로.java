import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int v;
		int w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();	
	static StringTokenizer st;
	
	static int V, E, SV;
	static int[] dist;
	static List<ArrayList<Node>> graph = new ArrayList<>();
	static Queue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		init();
		dijkstra(SV);
		print();
	}
	
	static void print() {
		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dijkstra(int start) {
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curIdx = curNode.v; 
			int curW = curNode.w;
			
			if (curW > dist[curIdx]) continue; // already optimal weight,
			
			for (Node next : graph.get(curIdx)) {
				int newW = dist[curIdx] + next.w;
				
				if (newW < dist[next.v]) {
					dist[next.v] = newW; // update min weight,
					pq.add(new Node(next.v, newW));
				}
			}
		}
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		SV = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
		
		dist = new int[V+1];
		for (int i = 1; i <= V; i++) dist[i] = Integer.MAX_VALUE;
		dist[SV] = 0; 
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}
		br.close();
	}
}
