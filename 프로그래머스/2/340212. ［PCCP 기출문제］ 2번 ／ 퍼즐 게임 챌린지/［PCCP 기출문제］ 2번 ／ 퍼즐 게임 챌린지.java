import java.util.*;

class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        int lo = 1;
        int hi = 100_000;
        
        while (lo < hi) {
            
            int mid = lo + (hi - lo) / 2;
            
            // 현재 레벨에서 퍼즐 풀이 시뮬레이션
            if (solve(diffs, times, limit, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    private boolean solve(int[] diffs, int[] times, long limit, int level) {
        
        long time = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int curDiff = diffs[i];
            int curTime = times[i];
            int prevTime = (i == 0) ? 0 : times[i-1];
            
            // 현재 퍼즐이 level로 풀어지는지 검사
            if (curDiff <= level) {
                // 풀어지면 풀이 
                time += curTime;
            } else {
                // 안풀어지면 재시도
                time += (long) (curDiff - level) * (curTime + prevTime) + curTime;
            }
        }
        
        // 풀이 소요 시간이 limit 이하인지 검사
        return time <= limit;
    }
    
}