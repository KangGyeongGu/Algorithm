import java.util.*;

class Solution {
    
    public int[] solution(int[][] edges) {
        
        int MAX = 1_000_000;
        int[] in = new int[MAX+1];
        int[] out = new int[MAX+1];
        
        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
        }
        
        int created = 0;
        
        for (int i = 1; i <= MAX; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                created = i;
                break;
            }
        }
        
        int stick = 0, eight = 0;
        
        for (int i = 1; i <= MAX; i++) {
            if (i == created) continue;
            
            if (out[i] == 0 && in[i] > 0) stick++;
            else if (out[i] == 2) eight++;
        }
        
        int donut = out[created] - stick - eight;
        
        return new int[]{created, donut, stick, eight};        
    }
}