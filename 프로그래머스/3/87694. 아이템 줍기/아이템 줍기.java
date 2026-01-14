import java.util.*;

class Solution {
    
    static final int SIZE = 102;
    static int[][] map = new int[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    
    static class Coord {
        int x, y, dist;
        
        Coord(int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;
            
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;
            
            for (int x = x1+1; x < x2; x++) {
                for (int y = y1+1; y < y2; y++) {
                    map[x][y] = 0;
                }
            }
        }
        
        Deque<Coord> dq = new ArrayDeque<>();
        dq.offer(new Coord(characterX * 2, characterY * 2, 0));
        visited[characterX * 2][characterY * 2] = true;
        
        while (!dq.isEmpty()) {
            Coord curr = dq.poll();
            
            if (curr.x == itemX * 2 && curr.y == itemY * 2) return curr.dist / 2;
            
            for (int[] dir : DIR) {
                int nx = curr.x + dir[0], ny = curr.y + dir[1];
                
                if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                dq.offer(new Coord(nx, ny, curr.dist+1));
            }
        }

        return 0;
    }
}