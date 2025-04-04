import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int T, N, M, result;
    static boolean[] iV;
    static List<ArrayList<Integer>> adj, radj;
    
    private static void init() throws IOException {
    	N = Integer.parseInt(br.readLine());
    	M = Integer.parseInt(br.readLine());
    	
    	adj = new ArrayList<>();
    	radj = new ArrayList<>();

    	for (int i = 0; i <= N; i++) {
    		adj.add(new ArrayList<>());
    		radj.add(new ArrayList<>());
    	}
    	
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int u = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		adj.get(u).add(v);
    		radj.get(v).add(u);
    	}
    	result = 0;
    }

    private static int dfs(int node, List<ArrayList<Integer>> graph) {
    	iV[node] = true;
    	int count = 0;
    	
    	for(int vertex : graph.get(node)) {
    		if (!iV[vertex]) {
    			count += 1 + dfs(vertex, graph);
    		}
    	}
    	return count;
    }
    
    private static void run() {
    	for (int i = 1; i <= N; i++) {
			iV = new boolean[N+1];
			int taller = dfs(i, adj);
			
			iV = new boolean[N+1];
			int smaller = dfs(i, radj);
			
			if (taller+smaller == N-1) result++;
		}
    }
    
    public static void main(String[] args) throws IOException {
    	T = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= T; tc++) {
    		init();
    		run();
    		sb.append("#" + tc + " " + result).append('\n');
    	}
    	System.out.println(sb);
    }
}