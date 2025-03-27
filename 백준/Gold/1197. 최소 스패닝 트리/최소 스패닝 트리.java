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
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int V, E;
	static int[] sets;
	static List<Edge> list = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		init();
		kruskal();
	}
	
	static void kruskal() {
		int CNT = 0, ANS = 0;
		
		for (int i = 0; i < E; i++) {
			Edge curE = list.get(i);
			
			if (!union(curE.u, curE.v)) continue;
			
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
	
	static boolean union(int a, int b) {
		a = find(a);
	    b = find(b);

	    if (a == b) return false;
	    if (sets[a] == sets[b]) sets[a]--;
	    if (sets[a] < sets[b]) sets[b] = a;
	    else sets[a] = b;
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
        
        sets = new int[V+1];
        Arrays.fill(sets, -1);
	}
}
