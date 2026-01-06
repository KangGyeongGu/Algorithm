class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (String a : arr) {
            int curr = Integer.parseInt(a);
            if (min > curr) min = curr;
            if (max < curr) max = curr;
        }
        
        
        return min + " " + max;
    }
}