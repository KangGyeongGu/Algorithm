import java.io.*;
import java.util.*;

public class Main {
	
	static final int UPPERBOUND = 100001;

	static int N, K;
	static List<Integer> route = new ArrayList<>();
	
	static int[] parent = new int[UPPERBOUND];
	static boolean[] iv = new boolean[UPPERBOUND];
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		
		for (int i = K; i != -1; i = parent[i]) route.add(i);
		Collections.reverse(route);
		
		System.out.println(route.size()-1);
		for (int e : route) {
			System.out.print(e + " ");
		}
	}
	
	private static void bfs() {
		Q.offer(N);
		iv[N] = true;
		parent[N] = -1;

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			if (cur == K) return;
			
			for (int child : new int[] {cur-1, cur+1, cur*2}) {
				if (0 <= child && child < UPPERBOUND && !iv[child]) {
					Q.offer(child);
					iv[child] = true;
					parent[child] = cur; 
				}
			}
		}
	}
}
