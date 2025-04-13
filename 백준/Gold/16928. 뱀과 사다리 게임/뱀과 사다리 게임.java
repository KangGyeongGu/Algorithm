import java.util.*;

public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<1) + (n<<3) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	static int N, M;
	static int[] map = new int[101], ladderOrSnake = new int[101];
	static boolean[] iv = new boolean[101];
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		N = read(); 
		M = read();
		
		for (int i = 0; i < N; i++) ladderOrSnake[read()] = read();
		for (int i = 0; i < M; i++) ladderOrSnake[read()] = read();
		
		Q.offer(1);
		iv[1] = true;

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			if (cur == 100) {
				System.out.println(map[100]);
				break;
			}
			
			for (int next : new int[] {cur+6, cur+5, cur+4, cur+3, cur+2, cur+1}) {
				if (next > 100) continue;
				
				if (ladderOrSnake[next] != 0) {
					next = ladderOrSnake[next];
				}
				
				if (!iv[next]) {
					iv[next] = true;
					map[next] = map[cur] + 1;
					Q.offer(next);
				}
			}
		}
	}
}