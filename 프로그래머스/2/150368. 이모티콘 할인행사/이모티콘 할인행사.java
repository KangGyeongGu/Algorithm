import java.util.*;

class Solution {
    
    private final int[] DISCOUNT = { 10, 20, 30, 40 };
    private int maxJoin = 0;
    private int maxSales = 0;
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] selected = new int[emoticons.length];
        dfs(0, users, emoticons, selected);
        return new int[]{maxJoin, maxSales};
    }
    
    private void dfs(int depth, int[][] users, int[] emoticons, int[] selected) {
        if (depth == emoticons.length) {
            simulate(users, emoticons, selected);
            return;
        }
        
        for (int discount : DISCOUNT) {
            selected[depth] = discount;
            dfs(depth+1, users, emoticons, selected);
        }
    }
    
    private void simulate(int[][] users, int[] emoticons, int[] selected) {
        int join = 0;
        int sales = 0;
        
        for (int[] user : users) {
            int needDiscount = user[0];
            int limitPrice = user[1];
            int sum = 0;
            
            for (int i = 0; i < emoticons.length; i++) {
                if (selected[i] >= needDiscount) {
                    sum += emoticons[i] * (100 - selected[i]) / 100;
                }
            }
            
            if (sum >= limitPrice) join++;
            else sales += sum;
        }
        
        if (join > maxJoin || (join == maxJoin && sales > maxSales)) {
            maxJoin = join;
            maxSales = sales;
        }
    }
}