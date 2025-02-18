import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M, result;
    static boolean[] iV;
    static List<ArrayList<Integer>> adj = new ArrayList<>(), radj = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        init();
        run();
        System.out.println(result);
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
    
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
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
    }
}
