import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, root, count;
	static List<List<Integer>> adj = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			
			if (parent == -1) {
				root = i;
				continue;
			}

			adj.get(parent).add(i);
			
		}
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			adj.get(i).remove(Integer.valueOf(M));
		}
		
		if (root == M) {
			System.out.println(0);
			return;
		}
		
		dfs(root);
		
		System.out.println(count);
		
	}
	
	private static void dfs(int start) {
		if (start == M) return;
		
		if (adj.get(start).isEmpty()) {
			count++;
			return;
		}
		
		for (int node : adj.get(start)) {
			dfs(node);
		}
		
	}
}