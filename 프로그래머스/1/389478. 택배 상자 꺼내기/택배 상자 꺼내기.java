import java.util.*;
import java.io.*;

class Solution {
    
    public int solution(int n, int w, int num) {
        int row = (num - 1) / w;
        int col = (num - 1) % w;
        
        if (row %2 == 1) col = w - 1 - col;
        
        int count = 1;
        
        for (int r = row + 1; r <= (n-1) / w; r++) {
            int boxNum;
            
            if (r % 2 == 0) {
                boxNum = r * w + col + 1;
            } else {
                boxNum = r * w + w - col;
            }
            
            if (boxNum <= n) {
                count++;
            }
        }
        
        return count;
    }
    
}