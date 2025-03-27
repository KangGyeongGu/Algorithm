import java.util.*;
import java.io.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	static int V, E;
	static int[] sets = new int[10001];
	static List<Edge> list = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		init();
		kruskal();
	}
	
	static void kruskal() {
		int CNT = 0, ANS = 0;
		
		for (int i = 0; i < E; i++) {
			Edge curE = list.get(i);
			
			if (!union(curE)) continue;
			
			ANS += curE.w;
			CNT++;
			
			if (CNT == V-1) break;
		}
		
		System.out.println(ANS);
	}
	
	static int find(int x) {
		if (sets[x] < 0) return x;
		return sets[x] = find(sets[x]);
	}
	
	static boolean union(Edge e) {
		e.u = find(e.u);
		e.v = find(e.v);
		
		if (e.u == e.v) return false;
		if (sets[e.u] == sets[e.v]) sets[e.u]--;
		if (sets[e.u] < sets[e.v]) sets[e.v] = e.u;
		else sets[e.u] = e.v;
		return true;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.add(new Edge(u, v, w));
        }
        
        Collections.sort(list);
        
        Arrays.fill(sets, -1);
	}
}