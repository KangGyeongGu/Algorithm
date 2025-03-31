import java.io.*;
import java.util.*;

public class Main {

	static class Planet {
		int id, x, y, z;

        public Planet(int id, int x, int y, int z) {
			this.id = id; this.x = x; this.y = y; this.z = z;
		}
		
		public static void calcCostByX() {
	        Arrays.sort(pList, Comparator.comparingInt(p -> p.x));
	        for (int i = 0; i < N - 1; i++) {
	            int cost = Math.abs(pList[i].x - pList[i + 1].x);
	            pQ.offer(new Tunnel(pList[i].id, pList[i + 1].id, cost));
	        }
	    }

	    public static void calcCostByY() {
	        Arrays.sort(pList, Comparator.comparingInt(p -> p.y));
	        for (int i = 0; i < N - 1; i++) {
	            int cost = Math.abs(pList[i].y - pList[i + 1].y);
	            pQ.offer(new Tunnel(pList[i].id, pList[i + 1].id, cost));
	        }
	    }

	    public static void calcCostByZ() {
	        Arrays.sort(pList, Comparator.comparingInt(p -> p.z));
	        for (int i = 0; i < N - 1; i++) {
	            int cost = Math.abs(pList[i].z - pList[i + 1].z);
	            pQ.offer(new Tunnel(pList[i].id, pList[i + 1].id, cost));
	        }
	    }
	}
	
	static class Tunnel implements Comparable<Tunnel> {
		int u, v;
		long cost;
        
		public Tunnel(int u, int v, long cost) {
			this.u = u; this.v = v; this.cost = cost;
		}
		
		@Override
		public int compareTo(Main.Tunnel o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static int N;
	static int[] set;
	static Planet[] pList;
	static PriorityQueue<Tunnel> pQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		init();
		kruskal();
	}
	
	private static void kruskal() {
		long ANSWER = 0;
		int count = 0;
		
		while (!pQ.isEmpty() && count < N-1) {
			Tunnel curT = pQ.poll();
			
			if (!union(curT.u, curT.v)) continue;
			
			ANSWER += curT.cost;
			count++;
		}
		
		System.out.println(ANSWER);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return false;
		if (set[a] == set[b]) set[a]--;
		if (set[a] < set[b]) set[b] = a;
		else set[a] = b;
		return true;
	}
	
	private static int find(int x) {
		if (set[x] < 0) return x;
		return set[x] = find(set[x]);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pList = new Planet[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			pList[i] = new Planet(i, x, y, z);
		}
		
		Planet.calcCostByX();
		Planet.calcCostByY();
		Planet.calcCostByZ();
		
		set = new int[N];
		Arrays.fill(set, -1);
	}
}