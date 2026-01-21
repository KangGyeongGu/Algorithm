import java.util.*;

class Solution {
    
    static class Node {
        String word;
        int depth;
        
        Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        if (!isValidCase(target, words)) return 0;
        return bfs(begin, target, words);
    }
    
    private boolean isValidCase(String target, String[] words) {
        for (String word : words) {
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean canChange(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
    
    private int bfs(String begin, String target, String[] words) {
        
        boolean[] visited = new boolean[words.length];
        Queue<Node> dq = new ArrayDeque<>();
        
        dq.offer(new Node(begin, 0));
        
        while(!dq.isEmpty()) {
            Node curr = dq.poll();
            
            if (curr.word.equals(target)) return curr.depth;
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canChange(curr.word, words[i])) {
                    visited[i] = true;
                    dq.offer(new Node(words[i], curr.depth+1));
                }
            }
        }
        
        return 0;
    }
}