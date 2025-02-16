import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, E, count;
	static boolean[] iV;
	static List<ArrayList<Integer>> con = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init(); br.close();
		dfs(1);
		System.out.println(count);
		
	}
	
	private static void dfs(int node) {
		iV[node] = true;
		
		for(int nextNode : con.get(node)) {
			if (!iV[nextNode]) {
				count++;
				dfs(nextNode);
			}
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		iV = new boolean[++N];
		
		for (int i = 0; i <= N; i++) {
			con.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			con.get(u).add(v);
			con.get(v).add(u);
		}
	}
}