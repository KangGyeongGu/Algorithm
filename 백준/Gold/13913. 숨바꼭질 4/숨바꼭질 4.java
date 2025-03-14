import java.io.*;
import java.util.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static final int UPPERBOUND = 100001;

	static int N, K, count;
	static int[] route;
	static int[] parent = new int[UPPERBOUND];
	static int[] iv = new int[UPPERBOUND];
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		
		count = iv[K];
		sb.append(count).append('\n');
		
		route = new int[count+1];
		int pNode = K;
		
		while (pNode != N) {
			route[count--] = pNode;
			pNode = parent[pNode];
		}
		route[count] = N;
		
		for (int i = 0; i < route.length; i++) {
			sb.append(route[i]).append(' ');
		}
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		Q.offer(N);

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			if (cur == K) return;
			
			for (int child : new int[] {cur-1, cur+1, cur*2}) {
				if (0 <= child && child < UPPERBOUND && iv[child] == 0) {
					Q.offer(child);
					iv[child] = iv[cur] + 1;
					parent[child] = cur; 
				}
			}
		}
	}
}
