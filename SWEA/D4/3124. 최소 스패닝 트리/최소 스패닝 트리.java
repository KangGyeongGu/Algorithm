import java.util.*;
import java.io.*;

public class Solution {
    
    static class Edge implements Comparable<Edge> {
        int v, w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    static int T, V, E;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            prim(tc);
        }
        System.out.println(sb);
    }
    
    private static void prim(int tc) {
        long ans = 0;
        int cnt = 0;
        
        pq.offer(new Edge(1, 0)); 
        while (!pq.isEmpty() && cnt < V) {
            Edge cur = pq.poll();
            
            if (visited[cur.v]) continue;
            
            visited[cur.v] = true;
            ans += cur.w;
            cnt++;
            
            for (Edge next : graph.get(cur.v)) {
                if (!visited[next.v]) pq.offer(next);
            }
        }
        
        sb.append("#").append(tc).append(" ").append(ans).append("\n");
    }
    
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        
        visited = new boolean[V + 1];
        pq = new PriorityQueue<>();
    }
}
