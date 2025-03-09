import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    static int T, n;
    static int[] graph;
    static boolean[] visited, finished;
    static int count;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
        	init(); 
        	
            for (int i = 1; i <= n; i++) if (!visited[i]) dfs(i);

            sb.append(n - count).append("\n");
        }

        System.out.print(sb);
    }
    

    static void dfs(int node) {
        visited[node] = true; 
        int next = graph[node];

        if (!visited[next]) {
            dfs(next); 
        } 
        else if (!finished[next]) {
            int temp = next;
            count++; 
            
            while (temp != node) {
                count++;
                temp = graph[temp];
            }
        }

        finished[node] = true;
    }
    
    static void init() throws IOException {
    	n = Integer.parseInt(br.readLine());
    	graph = new int[n + 1];
    	visited = new boolean[n + 1];
    	finished = new boolean[n + 1];
    	count = 0;
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 1; i <= n; i++) {
    		graph[i] = Integer.parseInt(st.nextToken());
    	}
    }
}
