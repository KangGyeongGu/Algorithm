import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		
		while (N-->0) {
			
			int n = Integer.parseInt(br.readLine());

			if (n==0) {
				if (pQ.isEmpty()) sb.append("0\n");
				else sb.append(pQ.poll()).append("\n");
			} else {
				pQ.offer(n);
			}

		}
		
		System.out.println(sb);
	}
}
