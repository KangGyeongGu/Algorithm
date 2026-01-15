import java.util.*;

class Solution {
    
    static class Coord {
        int x, y;
        
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            Coord p = (Coord) o;
            return x == p.x && y == p.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    private final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    static int n;
    static boolean[][] isVisited;
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        List<List<Coord>> blanks = extract(game_board, 0);
        List<List<Coord>> blocks = extract(table, 1);
        
        boolean[] used = new boolean[blocks.size()];
        int answer = 0;
        
        for(List<Coord> blank : blanks) {
            for (int i = 0; i < blocks.size(); i++) {
                if (used[i]) continue;
                if (blank.size() != blocks.get(i).size()) continue;
                
                if (isMatch(blank, blocks.get(i))) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }
        
        return answer;
    }
    
    private List<List<Coord>> extract(int[][] board, int target) {
        isVisited = new boolean[n][n];
        List<List<Coord>> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j] && board[i][j] == target) {
                    result.add(bfs(board, i, j, target));
                }
            }
        }
        
        return result;
    }
    
    private List<Coord> bfs(int[][] board, int x, int y, int target) {
        Queue<Coord> dq = new ArrayDeque<>();
        List<Coord> shape = new ArrayList<>();
        
        dq.offer(new Coord(x,y));
        isVisited[x][y] = true;
        shape.add(new Coord(x,y));
        
        while (!dq.isEmpty()) {
            Coord curr = dq.poll();
            
            for (int[] dir : DIR) {
                int nx = curr.x + dir[0], ny = curr.y + dir[1];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (isVisited[nx][ny] || board[nx][ny] != target) continue;
                
                isVisited[nx][ny] = true;
                dq.offer(new Coord(nx, ny));
                shape.add(new Coord(nx, ny));
            }
        }
        
        return normalize(shape);
    }
    
    private List<Coord> normalize(List<Coord> shape) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        
        for (Coord coord : shape) {
            minX = Math.min(coord.x, minX);
            minY = Math.min(coord.y, minY);
        }
        
        List<Coord> normalized = new ArrayList<>();
        for (Coord coord : shape) {
            normalized.add(new Coord(coord.x - minX, coord.y - minY));
        }
        
        normalized.sort(Comparator.comparingInt((Coord coord) -> coord.x).thenComparingInt(coord -> coord.y));
        
        return normalized;
    }
    
    private boolean isMatch(List<Coord> blank, List<Coord> block) {
        List<Coord> rotated = new ArrayList<>(block);
        
        for (int i = 0; i < 4; i++) {
            if (blank.equals(rotated)) return true;
            
            rotated = rotate(rotated);
        }
        return false;
    }
    
    private List<Coord> rotate(List<Coord> block) {
        List<Coord> rotated = new ArrayList<>();
        for (Coord coord : block) {
            rotated.add(new Coord(coord.y, -coord.x));
        }
        return normalize(rotated);
    }
}