import java.util.*;

class Solution {
    
    private int min = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        
        for (int cut = 0; cut < wires.length; cut++) {
            int count = bfs(n, wires, cut);
            int diff = Math.abs(n - 2 * count);
            min = Math.min(min, diff);
        }
        
        return min;
    }
    
    private int bfs(int n, int[][] wires, int cut) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < wires.length; i++) {
            if (i == cut) continue;
            int from = wires[i][0];
            int to = wires[i][1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        boolean[] visited = new boolean[n+1];
        Queue<Integer> dq = new ArrayDeque<>();
        
        dq.offer(1);
        visited[1] = true;
        int count = 1;
        
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            
            for (int next : graph.get(cur)) {
                if (visited[next]) continue;
                
                visited[next] = true;
                dq.offer(next);
                count++;
            }
        }
        
        return count;
    }
    
    
}