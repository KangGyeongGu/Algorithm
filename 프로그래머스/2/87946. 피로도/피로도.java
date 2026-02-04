class Solution {
    
    private int max = 0;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(0, k, dungeons, visited);
        return max;
    }
    
    private void dfs(int depth, int curK, int[][] dungeons, boolean[] visited) {
        max = Math.max(max, depth);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            if (curK < dungeons[i][0]) continue;
            
            visited[i] = true;
            dfs(depth + 1, curK - dungeons[i][1], dungeons, visited);
            visited[i] = false;
        }
    }
}