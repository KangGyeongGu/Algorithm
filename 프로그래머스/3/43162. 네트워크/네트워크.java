import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // bfs(i, n, computers, visited);
                dfs(i, n, computers, visited);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void dfs(int node, int n, int[][] computers, boolean[] visited) {
        visited[node] = true;
        
        for (int i = 0; i < n; i++) {
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, n, computers, visited);
            }
        }
    }
    
    private void bfs(int start, int n, int[][] computers, boolean[] visited) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int i = 0; i < n; i++) {
                if (computers[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
            
        }
        
    }
}