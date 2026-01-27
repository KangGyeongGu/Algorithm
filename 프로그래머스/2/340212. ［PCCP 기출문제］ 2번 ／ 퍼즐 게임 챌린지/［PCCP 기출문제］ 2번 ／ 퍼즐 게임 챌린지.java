import java.util.*;

class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        int lo = 1, hi = 100_000;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (solve(diffs, times, limit, mid)) hi = mid;
            else lo = mid + 1;
        }
        
        return lo;
    }
    
    private boolean solve(int[] diffs, int[] times, long limit, int level) {
        
        long time = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int curDiff = diffs[i];
            int curTime = times[i];
            int prevTime = (i == 0) ? 0 : times[i-1];
            
            if (curDiff <= level) time += curTime;
            else time += (long) (curDiff - level) * (curTime + prevTime) + curTime;
                
            if (time > limit) return false;
        }
        
        return time <= limit;
    }
    
}