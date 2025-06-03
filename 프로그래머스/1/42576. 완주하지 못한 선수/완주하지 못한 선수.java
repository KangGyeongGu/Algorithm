import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();
        
        for (String name: participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        
        for (String name: completion) {
            map.put(name, map.get(name) - 1);
        }
        
        // for (String name: map.keySet()) {
        //     if (map.get(name) >= 1) {
        //         return name;
        //     }
        // }
        
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getValue() >= 1) {
                return entry.getKey();
            }
        }
        
        return "";
    }
}