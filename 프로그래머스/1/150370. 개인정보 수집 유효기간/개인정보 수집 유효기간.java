import java.util.*;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int stdDate = toDays(today);
        
        Map<String, Integer> termMap = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] split = terms[i].split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]) * 28);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            
            int currDate = toDays(split[0]);
            int expDate = currDate + termMap.get(split[1]);
            
            if (expDate <= stdDate) {
                result.add(i+1);
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    private int toDays(String Date) {
        String[] date = Date.split("\\.");
        return Integer.parseInt(date[0]) * 12 * 28 + Integer.parseInt(date[1]) * 28 + Integer.parseInt(date[2]);
    }
}