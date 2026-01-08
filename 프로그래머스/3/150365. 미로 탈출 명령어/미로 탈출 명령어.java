class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        
        int cx = x, cy = y;
        
        int dist = Math.abs(cx - r) + Math.abs(cy - c);
        if (dist > k || (k-dist)%2 != 0) return "impossible";
        
        int[][] iDir = { {1,0}, {0,-1}, {0,1}, {-1,0} };
        char[] cDir = {'d', 'l', 'r', 'u'};
        
        for (int step = 0; step < k; step++) {
            boolean moved = false;
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + iDir[i][0];
                int ny = cy + iDir[i][1];
                
                if (n < nx || nx < 1 || m < ny || ny < 1) continue;
                
                int remain = k - step - 1;
                int need = Math.abs(nx-r) + Math.abs(ny-c);
                
                if (need > remain) continue;
                if ((remain-need)%2 != 0) continue;
                
                sb.append(cDir[i]);
                cx = nx;
                cy = ny;
                moved = true;
                break;
            }
            
            if (!moved) return "impossible";
        }
        
        return sb.toString();
    }
}