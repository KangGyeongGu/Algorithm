import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static PriorityQueue<Long> pQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		while (N-->0) {
			long in = Long.parseLong(br.readLine());
			
			if (in == 0) {
				if (pQ.isEmpty()) sb.append(0).append('\n');
				else sb.append(pQ.poll()).append('\n');
			}
			else {
				pQ.offer(in);
			}
		}
		
		System.out.println(sb);
	}
}
