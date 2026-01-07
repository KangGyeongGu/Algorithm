import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> kindSet = new HashSet<>(Arrays.asList(gems));
        int totalKinds = kindSet.size();
        
        Map<String, Integer> countMap = new HashMap<>();
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        
        for (int right = 0; right < gems.length; right++) {
            countMap.put(gems[right], countMap.getOrDefault(gems[right], 0) + 1);
            
            while (countMap.size() == totalKinds) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                    end = right;
                }
                
                String leftGem = gems[left];
                countMap.put(leftGem, countMap.get(leftGem)-1);
                if (countMap.get(leftGem) == 0) {
                    countMap.remove(leftGem);
                }
                left++;
            }
        }
        
        return new int[]{start+1, end+1};
    }
}