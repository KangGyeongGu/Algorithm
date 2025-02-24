import java.io.*;
import java.util.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static int N, K;
	static ArrayDeque<Integer> seq = new ArrayDeque<>();
	
    public static void main(String[] args) throws IOException {
    	init();
    	Yosep();
    	System.out.println(sb);
    }
    
    static void Yosep() {
    	if (K==1) {
    		for (int i = 0; i < N-1; i++) {
				sb.append(seq.pollFirst()).append(", ");
			}
    		sb.append(seq.pollFirst()).append(">");
    	}
    	else {
    		while (seq.size() > 1) {
    			for (int i = 0; i < K-1; i++) {
					seq.addLast(seq.pollFirst());
				}
    			sb.append(seq.pollFirst()).append(", ");
    		}
    		sb.append(seq.pollFirst()).append(">");
    	}
    }
    
    static void init() throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	sb.append("<");
    	String[] in = br.readLine().split(" ");
    	N = Integer.parseInt(in[0]);
    	K = Integer.parseInt(in[1]);
    	for (int i = 1; i <= N; i++) seq.addLast(i);
    	br.close();
    }
}