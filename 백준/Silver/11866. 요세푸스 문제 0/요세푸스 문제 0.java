import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> q = new ArrayDeque<>();
		List<Integer> rst = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) q.offer(i);
		
		while (!q.isEmpty()) {
			
			for (int i = 0; i < K-1; i++) {
				q.offer(q.pollFirst());
			}
			rst.add(q.pollFirst());
		}
		
		for (int i = 0; i < N-1; i++) {
			sb.append(rst.get(i) + ", ");
		}
		sb.append(rst.get(N-1) + ">");
		
		System.out.println(sb);
	}
	
}