import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	
	static ArrayDeque<Character> left, right;
	
    public static void main(String[] args) throws IOException {
    	
    	N = Integer.parseInt(br.readLine());
    	for (int i = 0; i < N; i++) {
    		
    		left = new ArrayDeque<>(); right = new ArrayDeque<>();
    		char[] in = br.readLine().toCharArray();
    		
    		for (int j = 0; j < in.length; j++) {
    			char ch = in[j];
    			cursor(ch);
			}
    		
    		while (!left.isEmpty()) {
    			sb.append(left.pollFirst());
    		}
    		while (!right.isEmpty()) {
    			sb.append(right.pollFirst());
    		}
    		sb.append('\n');
		}
    	System.out.println(sb);
    }
    
    static void cursor(char in) {
    	if (in == '<') {
    		if (!left.isEmpty()) right.addFirst(left.pollLast());
    	}
    	else if (in == '>') {
    		if (!right.isEmpty()) left.addLast(right.pollFirst());
    	}
    	else if (in == '-') {
    		if (!left.isEmpty()) left.pollLast();
    	}
    	else {
    		left.addLast(in);
    	}
    }
}
