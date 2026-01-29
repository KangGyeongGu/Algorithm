class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        
        boolean[] hand = new boolean[n+1];
        boolean[] paid = new boolean[n+1];
        
        for (int i = 0; i < n/3; i++) {
            hand[cards[i]] = true;
            paid[cards[i]] = true;
        }
        
        int round = 1;
        int leftCoin = coin;
        for (int i = n/3; i < n; i+=2) {
            if (leftCoin > 0) {
                hand[cards[i]] = true;
                hand[cards[i+1]] = true;
            }
            
            boolean pass = false;
            int minCost = 2;
            int cardThrown = -1;
            for (int j = 1; j <= n; j++) {
                if (!hand[j]) continue;
                
                if (hand[n+1-j]) {
                    int cost = (paid[j] ? 0 : 1) + (paid[n+1-j] ? 0 : 1);
                    
                    if (leftCoin < cost || minCost < cost) continue;
                    
                    pass = true;
                    cardThrown = j;
                    minCost = cost;
                }
            }
            
            if (!pass) break;
            
            hand[cardThrown] = false;
            hand[n + 1 - cardThrown] = false;
            leftCoin -= minCost;
            
            round++;
        }
        
        return round;
    }
}