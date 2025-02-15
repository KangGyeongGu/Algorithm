import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int k;
	static int[] S;
	static int[] C = new int[6];
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while (true) {
			if (init()) break;
			dfs(0, 0);
			sb.append('\n');
		}
		System.out.println(sb);
		br.close();
	}
	
	// combination BFS
	private static void dfs(int start, int depth) {
		if (depth == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(C[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for (int i = start; i < k; i++) {
			C[depth] = S[i];
			dfs(i+1, depth+1);
		}
	}
	
	// test case initializer
	private static boolean init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		if (k == 0) return true;
		
		S = new int[k];
		for (int i = 0; i < k; i++) S[i] = Integer.parseInt(st.nextToken()); 
		return false;
	}
	
}
