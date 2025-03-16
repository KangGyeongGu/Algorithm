import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] p;
	static List<List<Integer>> adj = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(1);
		for (int i = 2; i <= N; i++) System.out.println(p[i]);
	}
	
	private static void dfs(int start) {
		for (int elem : adj.get(start)) {
			if (p[elem] != 0) continue;
			p[elem] = start;
			dfs(elem);
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		p = new int[N+1];
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
	}
}