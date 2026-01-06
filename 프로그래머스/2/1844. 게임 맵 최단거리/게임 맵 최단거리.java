import java.util.*;

class Pos {
    int x;
    int y;
    int depth;
    
    Pos(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}

class Solution {
    
    private final int WALL = 0, GROUND = 1;
    private final int[][] DIR = { {1,0}, {0,1}, {-1,0}, {0,-1} };
    
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    
    private int bfs(int[][] maps) {
        Queue<Pos> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        dq.offer(new Pos(0,0,1));
        visited[0][0] = true;
        
        while (!dq.isEmpty()) {
            Pos curr = dq.poll();
            
            if (curr.x == maps.length-1 && curr.y == maps[0].length-1) return curr.depth;
            
            for (int[] dir : DIR) {
                int nx = curr.x + dir[0];
                int ny = curr.y + dir[1];
                
                if (isMovable(nx, ny, maps) && maps[nx][ny] != WALL && !visited[nx][ny]) {
                    dq.offer(new Pos(nx, ny, curr.depth+1));
                    visited[nx][ny] = true;
                }
            }
        }
        
        return -1;
    }
    
    private boolean isMovable(int x, int y, int[][] maps) {
        return 0 <= x && x < maps.length && 0 <= y && y < maps[0].length;
    }
}