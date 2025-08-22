import java.util.*;

class Solution {
    
    final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    
    public int solution(String[][] board, int h, int w) {
        
        int answer = 0;
        String baseColor = board[h][w];
        
        for (int[] dir : DIR) {
            int nx = h + dir[0], ny = w + dir[1];
            
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            
            if (board[nx][ny].equals(baseColor)) answer++;
        }
        
        return answer;
    }
}