import java.util.*;

class Solution {
    
    private final int SIZE = 50;
    private int[] parent = new int[SIZE * SIZE];
    private String[] value = new String[SIZE * SIZE];
    
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < SIZE * SIZE; i++) {
            parent[i] = i;
            value[i] = null;
        }
        
        for (String command : commands) {
            String[] parts = command.split(" ");
            String type = parts[0];
            
            if (type.equals("UPDATE")) {
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]) - 1;
                    int c = Integer.parseInt(parts[2]) - 1;
                    String v = parts[3];
                    
                    int root = find(idx(r, c));
                    value[root] = v;
                } else {
                    String from = parts[1];
                    String to = parts[2];
                    
                    for (int i = 0; i < SIZE * SIZE; i++) {
                        if (parent[i] == i && from.equals(value[i])) value[i] = to;
                    }
                }
            }
            
            else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(parts[1]) - 1;
                int c1 = Integer.parseInt(parts[2]) - 1;
                int r2 = Integer.parseInt(parts[3]) - 1;
                int c2 = Integer.parseInt(parts[4]) - 1;
                
                int a = find(idx(r1, c1));
                int b = find(idx(r2, c2));
                
                if (a == b) continue;
                
                if (value[a] != null) parent[b] = a;
                else parent[a] = b;
                
                int root = find(a);
                int other = root == a ? b : a;
                value[root] = value[root] != null ? value[root] : value[other];
            }
            
            else if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(parts[1]) - 1;
                int c = Integer.parseInt(parts[2]) - 1;
                
                int target = idx(r, c);
                int root = find(target);
                String keep = value[root];
                
                List<Integer> members = new ArrayList<>();
                for (int i = 0; i < SIZE * SIZE; i++) {
                    if (find(i) == root) members.add(i);
                }
                
                for (int member : members) {
                    parent[member] = member;
                    value[member] = null;
                }
                
                value[target] = keep;
            }
            
            else if (type.equals("PRINT")) {
                int r = Integer.parseInt(parts[1]) - 1;
                int c = Integer.parseInt(parts[2]) - 1;
                
                int root = find(idx(r, c));
                result.add(value[root] == null ? "EMPTY" : value[root]);
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    private int idx(int r, int c) {
        return r * SIZE + c;
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}