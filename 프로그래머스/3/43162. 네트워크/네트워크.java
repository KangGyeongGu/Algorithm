import java.util.*;

class Solution {
    
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networks = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            bfs(i, computers, visited);
            networks++;
        }
        
        return networks;
    }
    
    private void bfs(int start, int[][] graph, boolean[] visited) {
        Deque<Integer> dq = new ArrayDeque<>();
        visited[start] = true;
        dq.offer(start);
        
        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next = 0; next < graph.length; next++) {
                if (graph[cur][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    dq.offer(next);
                }
            }
        }
    }
    
    
}