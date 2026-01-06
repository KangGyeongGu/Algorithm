import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] visited = new boolean[words.length];
        Deque<String> dq = new ArrayDeque<>();
        Deque<Integer> depth = new ArrayDeque<>();
        
        dq.offer(begin);
        depth.offer(0);
        
        while (!dq.isEmpty()) {
            String curr = dq.poll();
            int d = depth.poll();
            
            if (curr.equals(target)) {
                return d;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && isOneDiff(curr, words[i])) {
                    dq.offer(words[i]);
                    visited[i] = true;
                    depth.offer(d+1);
                }
            }
        }
        
        return 0;
    }
    
    private boolean isOneDiff(String a, String b) {
        int diff = 0;
        
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        
        return diff == 1;
    }
}