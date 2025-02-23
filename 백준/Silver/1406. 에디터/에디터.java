import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int M;
	static String input;
	static ArrayDeque<Character> left = new ArrayDeque<>(), right = new ArrayDeque<>(); 
	
    public static void main(String[] args) throws IOException {
    	
    	input = br.readLine();
    	for (int i = 0; i < input.length(); i++) {
			left.offer(input.charAt(i));
		}
    	
    	M = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "P": 
				left.addLast(st.nextToken().charAt(0));
				break;
			
			case "L":
				if(!left.isEmpty()) right.addFirst(left.pollLast());
				break;
				
			case "D":
				if(!right.isEmpty()) left.addLast(right.pollFirst());
				break;
				
			case "B":
				if (!left.isEmpty()) left.pollLast();
			}
		}
    	
    	while (!left.isEmpty()) sb.append(left.pollFirst());
    	while (!right.isEmpty()) sb.append(right.pollFirst());
    	
    	System.out.println(sb);
    }
}
