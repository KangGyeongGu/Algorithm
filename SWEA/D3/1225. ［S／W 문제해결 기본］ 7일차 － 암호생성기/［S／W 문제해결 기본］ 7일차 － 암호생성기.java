import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class Solution {
	
	static final int TESTCASE = 10;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static Queue<Integer> seq;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		for (int tc = 1; tc <= TESTCASE; tc++) {
			init();
			genCypter();
			genOutput();
		}
		System.out.println(sb);
	}
	
	static void genCypter() {
		boolean FLAG = false;
		
		while (!FLAG) {
			for (int i = 1; i <= 5; i++) {
				int cur = seq.poll() - i;
				
				if (cur <= 0) { 
					cur = 0; FLAG = true; // satisfies condition,
					seq.offer(cur); // go back side
					break; // test case out
				}
				seq.offer(cur);
			}
		}
	}
	
	static void genOutput() {
		for (int e : seq) sb.append(e).append(" ");
		sb.append('\n');
	}
	
	static void init() throws IOException {
		int tc = Integer.parseInt(br.readLine());
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		seq = new ArrayDeque<>();
		for (int i = 0; i < 8; i++) seq.offer(Integer.parseInt(st.nextToken()));
	}
}
