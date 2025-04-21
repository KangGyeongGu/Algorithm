import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static class Wire implements Comparable<Wire> {
		int to;
		double cost;

		public Wire(int to, double cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Wire o) {
			return Double.compare(this.cost, o.cost);
		}
	}

	static int N, W;
	static double M;
	static int[][] coords;
	static List<List<Wire>> graph;
	static PriorityQueue<Wire> pq;

	private static double getDistance(int i, int j) {
		int dx = coords[i][0] - coords[j][0];
		int dy = coords[i][1] - coords[j][1];
		return Math.sqrt((long) dx * dx + (long) dy * dy);
	}

	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());

		coords = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < W; i++) { // 전체 그래프 정보 입력
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Wire(v, 0));
			graph.get(v).add(new Wire(u, 0));
		}

		for (int i = 1; i <= N; i++) { // 제한 길이 간선만 갱신
			for (int j = i + 1; j <= N; j++) {
				double dist = getDistance(i, j);
				if (dist <= M) {
					graph.get(i).add(new Wire(j, dist));
					graph.get(j).add(new Wire(i, dist));
				}
			}
		}
	}

	private static void dijkstra() {
		double[] dist = new double[N + 1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[1] = 0;

		pq = new PriorityQueue<>();
		pq.add(new Wire(1, 0));

		while (!pq.isEmpty()) {
			Wire cur = pq.poll();
			int now = cur.to;


	        
			
			if (cur.cost > dist[now])
				continue;

			for (Wire next : graph.get(now)) {
				double newCost = dist[now] + next.cost;
				if (newCost < dist[next.to]) {
					dist[next.to] = newCost;
					pq.add(new Wire(next.to, newCost));
				}
			}
		}

		System.out.println((long) (dist[N] * 1000));
	}

	public static void main(String[] args) throws Exception {
		init();
		dijkstra();
	}
}
