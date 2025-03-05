import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, stx, etx;
	static int[] djk;
	static boolean[] iv;
	static List<ArrayList<int[]>> adj = new LinkedList<>();
	static PriorityQueue<int[]> PQ = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
	
	public static void main(String[] args) throws IOException {
		init();	
		dijkstra(stx);
		System.out.println(djk[etx]);
	}
	
	private static void dijkstra(int stx) {
		PQ.offer(new int[] {stx, 0});
		djk[stx] = 0;
		
		while (!PQ.isEmpty()) {
			int[] cur = PQ.poll();
			
			if (iv[cur[0]]) continue;
			
			iv[cur[0]] = true;
			
			for (int[] next : adj.get(cur[0])) {
				if (iv[next[0]] || djk[next[0]] <= cur[1] + next[1]) continue;
				
				djk[next[0]] = cur[1] + next[1];
				PQ.offer(new int[] { next[0], djk[next[0]] });
			}
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		djk = new int[N+1];
		iv = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
			djk[i] = Integer.MAX_VALUE; 
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj.get(u).add(new int[] {v, w});
		}
		
		st = new StringTokenizer(br.readLine());
		stx = Integer.parseInt(st.nextToken());
		etx = Integer.parseInt(st.nextToken());
	}
}