import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, cnt;
	static boolean[] iv;
	static PriorityQueue<Integer> Q = new PriorityQueue<>();
	static Set<Integer> FaF = new HashSet<>();
	static List<ArrayList<Integer>> adj = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int fr : adj.get(1)) {
			if (iv[fr]) continue;
			iv[fr] = true;
			FaF.add(fr);
			Q.offer(fr);
		}
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			for (int ffr : adj.get(cur)) {

				if (iv[ffr]) continue;
				iv[ffr] = true;
				FaF.add(ffr);
			}
		}
		
		for (int i = 2; i <= N; i++) {
			if(iv[i]) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		iv = new boolean[N+1];
	}
}
