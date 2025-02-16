import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, count;
	static boolean[] iV;
	static List<ArrayList<Integer>> conn = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		run();
		System.out.println(count);
	}
	
	private static void run() {
		for (int i = 1; i <= N; i++) {
			if (!iV[i]) {
				dfs(i);
				count++;
			}
		}
	}
	
	private static void dfs(int node) {
		iV[node] = true;
		
		for (int nextNode : conn.get(node)) {
			if (!iV[nextNode]) {
				iV[nextNode] = true;
				dfs(nextNode);
			}
		}
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		iV = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			conn.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			conn.get(u).add(v);
			conn.get(v).add(u);
		}
		
		br.close();
	}
}