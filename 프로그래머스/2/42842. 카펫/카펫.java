class Solution {
    
    public int[] solution(int brown, int yellow) {

        for (int h = 1; h * h <= yellow; h++) {
            if (yellow % h != 0) continue;
            
            int w = yellow / h;
            
            int totalBrown = (w + 2) * (h + 2) - yellow;
            if (totalBrown == brown) {
                int width = Math.max(w+2, h+2);
                int height = Math.min(w+2, h+2);
                return new int[]{ width, height };
            }
        }
        
        return null;
    }
}

