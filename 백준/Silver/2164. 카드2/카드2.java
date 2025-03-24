import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static Deque<Integer> deq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) deq.offer(i);
		
		while (deq.size() > 1) {
			deq.poll();
			deq.offerLast(deq.poll());
		}
		
		System.out.println(deq.poll());
	}
	
}
