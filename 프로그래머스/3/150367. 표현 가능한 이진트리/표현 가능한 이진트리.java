import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            
            int size = 1;
            while (size < binary.length()) {
                size = size * 2 + 1;
            }
            
            String padded = "0".repeat(size - binary.length()) + binary;
            
            answer[i] = isValidTree(padded) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean isValidTree(String tree) {
        return check(tree, 0, tree.length() - 1);
    }
    
    private boolean check(String tree, int start, int end) {
        if (start > end) return true;
        
        int mid = (start + end) / 2;
        char root = tree.charAt(mid);
        
        if (root == '0') {
            for (int i = start; i <= end; i++) {
                if (tree.charAt(i) == '1') return false;
            }
            return true;
        }
        
        return check(tree, start, mid - 1) && check(tree, mid + 1, end);
    }
}