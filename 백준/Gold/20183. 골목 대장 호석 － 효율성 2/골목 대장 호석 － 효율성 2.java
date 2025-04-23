import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Alley implements Comparable<Alley> {
		int v;
		long w;

		public Alley(int v, long w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Alley o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int N, M, A, B;
	static long C, max, dist[];
	static List<List<Alley>> adj;
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());

		max = Long.MIN_VALUE;
		
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			max = Math.max(max, c);
			
			adj.get(a).add(new Alley(b, c));
			adj.get(b).add(new Alley(a, c));
		}
		
		dist = new long[N+1];
	}
	
	private static boolean dijkstra(long cost) { // 최솟값
		PriorityQueue<Alley> pQ = new PriorityQueue<>();
		pQ.offer(new Alley(A, 0));
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[A] = 0;
		
		while (!pQ.isEmpty()) {
			Alley cur = pQ.poll();
			
			if (dist[cur.v] < cur.w) continue;
			
			for (Alley next : adj.get(cur.v)) {
				long nextCost = cur.w + next.w;
				
				if (cost < next.w || dist[next.v] <= nextCost) continue;
				
				dist[next.v] = nextCost;
				pQ.offer(new Alley(next.v, nextCost));
			}
		}
		
		return dist[B] <= C;
	}
	
	private static void binarySearch() { // 최댓값 중의 최솟값
		long answer = -1, start = 0, end = max;
		
		while (start <= end) {
			long mid = (start+end) / 2;
			
			if (dijkstra(mid)) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		binarySearch();
	}
}
