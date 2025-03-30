import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int T, L;
	static char[] ops;
	static Deque<Integer> deq = new ArrayDeque<>();
	static boolean isInOrder;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		while (T-->0) {
			ops = br.readLine().toCharArray();
			int L = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), "[],");
			
			for (int i = 0; i < L; i++) deq.offer(Integer.parseInt(st.nextToken()));
			
			command();
		}
		System.out.println(sb);
	}
	
	private static void command() {
		isInOrder = true;
		
		for (char op : ops) {
			if (op == 'R') {
				isInOrder = !isInOrder;
				continue;
			}
			
			if (isInOrder) {
				if (deq.pollFirst() == null) {
					sb.append("error\n");
					return;
				}
			}
			else {
				if (deq.pollLast() == null) {
					sb.append("error\n");
					return;
				}
			}
		}
		
		printer();
	}
	
	private static void printer() {
		sb.append("[");
		
		if (deq.size() > 0) {
			if (isInOrder) {
				sb.append(deq.pollFirst());
				while (!deq.isEmpty()) sb.append(",").append(deq.pollFirst());
			}
			else {
				sb.append(deq.pollLast());
				while (!deq.isEmpty()) sb.append(",").append(deq.pollLast());
			}
		}
		
		sb.append("]").append('\n');
	}
}