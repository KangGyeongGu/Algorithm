import java.util.*;

class Solution {
    
    private Map<String, List<String>> graph = new HashMap<>();
    private List<String> path = new ArrayList<>();
    private int ticketCount;
    private boolean isFinished = false;
    
    public String[] solution(String[][] tickets) {
        ticketCount = tickets.length;
        
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(ticket[1]);
        }
        
        for (List<String> list : graph.values()) {
            Collections.sort(list);
        }
        
        path.add("ICN");
        dfs("ICN");
        
        return path.toArray(new String[0]);
    }
    
    private void dfs(String curr) {
        if (isFinished) return;
        
        if (path.size() == ticketCount + 1) {
            isFinished = true;
            return;
        }
        
        List<String> nextList = graph.get(curr);
        if (nextList == null) return;
        
        for (int i = 0; i < nextList.size(); i++) {
            String next = nextList.remove(i);
            path.add(next);
            
            dfs(next);
            
            if (isFinished) return;
            
            path.remove(path.size()-1);
            nextList.add(i, next);
        }
    }
}