import java.util.*;
import java.io.*;

public class Main {
	
	static final int UPPERBOUND = 500001;
	
	static int N, K, time;
	static boolean[][] map = new boolean[UPPERBOUND][2];
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}

	private static int bfs() {
		time = 0;
		map[N][0] = true;
		
		Q.offer(N);
		
		while (!Q.isEmpty()) {
			if (K >= UPPERBOUND) return -1;
			
			if (map[K][time%2]) return time;
			
			int qSize = Q.size();
			while (qSize-->0) {
				
				int cur = Q.poll();
				int nextTime = (time+1)%2;
				
				for (int next : new int[] {cur-1, cur+1, cur*2}) {
					if (0 <= next && next < UPPERBOUND && !map[next][nextTime]) {
						map[next][nextTime] = true;
						Q.offer(next);
					}
				}
			}
			K += ++time;
		}
		return -1;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
}