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
	
	static class Tunnel implements Comparable<Tunnel> {
		Island u, v;
		long l;
		
		public Tunnel(Island u, Island v) {
			this.u = u;
			this.v = v;
			this.l = calcDist();
		}
		
		private long calcDist() {
			return (long) (Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2));
		}
		
		@Override
		public int compareTo(Tunnel o) {
			return Double.compare(this.l, o.l);
		}
	}
	
	static int T, N;
	static double RATE;
	static int[] set;
	static PriorityQueue<Tunnel> PQ = new PriorityQueue<>();
	
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
		
		while (!PQ.isEmpty() && cnt < N-1) {
			Tunnel curT = PQ.poll();
			
			if (!union(curT.u.id, curT.v.id)) continue;
			
			ans += curT.l;
			cnt++;
		}
		
		sb.append("#" + tc + " " + Math.round(RATE * ans) + '\n');
	}
	
	private static int find(int x) {
		if (set[x] < 0) return x;
		return set[x] = find(set[x]);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return false;
		if (set[a]==set[b]) set[a]--;
		if(set[a]<set[b]) set[b] = a;
		else set[a] = b;
		return true;
	}
	
	private static void init(int tc) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		int[] xCoords = new int[N];
		int[] yCoords = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) xCoords[i] = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) yCoords[i] = Integer.parseInt(st.nextToken());
		
		
		List<Island> islands = new ArrayList<>(N); 
		int idx = 0; 
		for (int i = 0; i < N; i++) islands.add(new Island(xCoords[i], yCoords[i], idx++));
		
		
		RATE = Double.parseDouble(br.readLine());
		
		PQ.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i==j) continue;
				PQ.offer(new Tunnel(islands.get(i), islands.get(j)));
			}
		}
		
		set = new int[N+1];
		Arrays.fill(set, -1);
	}
}