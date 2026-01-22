import java.util.*;

class Solution {
    
    static class State {
        int r, b, rMask, bMask, dist;
        
        State(int r, int b, int rMask, int bMask, int dist) {
            this.r = r; this.b = b;
            this.rMask = rMask; this.bMask = bMask;
            this.dist = dist;
        }
    }
    
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    static int n, m;
    static int rStart = -1, bStart = -1, rGoal = -1, bGoal = -1;
    
    public int solution(int[][] maze) {
        init(maze);
        
        if (rStart == rGoal && bStart == bGoal) return 0;
        
        return bfs(maze);
    }
    
    private int bfs(int[][] maze) {
        int initRMask = 1 << rStart, initBMask = 1 << bStart;
        
        Queue<State> dq = new ArrayDeque<>();
        dq.offer(new State(rStart, bStart, initRMask, initBMask, 0));
        
        HashSet<Long> visited = new HashSet<>();
        visited.add(pack(rStart, bStart, initRMask, initBMask));
        
        while (!dq.isEmpty()) {
            State curr = dq.poll();
            int r = curr.r, b = curr.b, rMask = curr.rMask, bMask = curr.bMask, dist = curr.dist;
            
            int[] rNext = generateMoves(r, rGoal, rMask, maze);
            int[] bNext = generateMoves(b, bGoal, bMask, maze);
            
            for (int nr : rNext) {
                int nrMask = rMask;
                if (nr != r) nrMask |= (1 << nr);
                
                for (int nb : bNext) {
                    int nbMask = bMask;
                    
                    if (nb != b) nbMask |= (1 << nb);
                    
                    if (nr == nb) continue;
                    
                    if (nr == b && nb == r) continue;
                    
                    if (nr == rGoal && nb == bGoal) return dist + 1;
                    
                    long key = pack(nr, nb, nrMask, nbMask);
                    if (visited.add(key)) {
                        dq.offer(new State(nr, nb, nrMask, nbMask, dist+1));
                    }
                }
            }
        }
        
        return 0;
    }
    
    private int[] generateMoves(int pos, int goal, int mask, int[][] maze) {
        if (pos == goal) return new int[]{pos};
        
        int x = pos / m, y = pos % m;
        
        int[] tmp = new int[4];
        int cnt = 0;
        
        for (int[] dir : DIR) {
            int nx = x + dir[0], ny = y + dir[1], nPos = nx * m + ny;
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (maze[nx][ny] == 5) continue;
            if ((mask & (1 << nPos)) != 0) continue;
            
            tmp[cnt++] = nPos;
        }
        
        return Arrays.copyOf(tmp, cnt);
    }
    
    private long pack(int r, int b, int rMask, int bMask) {
        long key = 0;
        key |= (long) (r);
        key |= (long) (b) << 4;
        key |= (long) (rMask) << 8;
        key |= (long) (bMask) << 24;
        return key;
    }
    
    private void init(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        rStart = -1;
        bStart = -1;
        rGoal = -1;
        bGoal = -1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = maze[i][j], idx = i * m + j;
                
                if (curr == 1) rStart = idx;
                else if (curr == 2) bStart = idx;
                else if (curr == 3) rGoal = idx;
                else if (curr == 4) bGoal = idx;
            }
        }
    }
    
    
}