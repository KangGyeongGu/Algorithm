import java.util.*;
import java.io.*;

public class Main {

	static class Lan implements Comparable<Lan>{
		int u, v, w;
		public Lan(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		
		@Override
		public int compareTo(Lan o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int N, M;
	static int[] set;
	static PriorityQueue<Lan> pQ = new PriorityQueue<>();
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException {
		init();
		kruskal();
	}
	
	private static void kruskal() {
		int cost = 0, cnt = 0;
		while (!pQ.isEmpty() && cnt < N-1) {
			
			Lan curL = pQ.poll();
			
			if (!union(curL.u, curL.v)) continue;
			
			cost += curL.w;
			cnt++;
		}
		System.out.println(cost);
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
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pQ.offer(new Lan(u, v, w));
		}
		
		set = new int[N+1];
		Arrays.fill(set, -1);
	}
}