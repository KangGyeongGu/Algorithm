import java.util.*;

class Solution {
    
    int[][] ways = {
        {1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}
    };
    
    public int[] solution(int[] answers) {
      
        int[] score = new int[3];
        int fIdx = 0, sIdx = 0, tIdx = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == ways[0][fIdx++]) score[0]++;
            if (answers[i] == ways[1][sIdx++]) score[1]++;
            if (answers[i] == ways[2][tIdx++]) score[2]++;
            
            if (fIdx == 5) fIdx = 0;
            if (sIdx == 8) sIdx = 0;
            if (tIdx == 10) tIdx = 0;
        }
        
        int max = Math.max(score[0], Math.max(score[1], score[2]));
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            if (score[i] == max) result.add(i+1);
        }
        
        return result.stream().mapToInt(i -> i).toArray();
        
        
    }
}