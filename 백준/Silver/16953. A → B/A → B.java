import java.util.*;
import java.io.*;

public class Main {

	static int A, B;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		sc.close();
		System.out.println(bfs(A));
	}
	
	private static int bfs(int a) {
		Queue<Long> Q = new ArrayDeque<>();
		Map<Long, Integer> iv = new HashMap<>();
		
		Q.offer((long) a);
		iv.put((long) a, 1);
		
		while (!Q.isEmpty()) {
			long cur = Q.poll();
			
			if (cur == B) return iv.get(cur);
			
			for (long next : new long[] {cur*2, cur*10+1}) {
				if (0 <= next && next <= B && !iv.containsKey(next)) {
					iv.put(next, iv.get(cur)+1);
					Q.offer(next);
				}
			}
		}
		
		return -1;
	}
}