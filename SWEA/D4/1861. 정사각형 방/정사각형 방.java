import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int T, N;
    static int[][] room, memo;
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    
    public static void main(String[] args) throws IOException {
        run();
    }
    
    private static void run() throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init(tc);
            solve();
        }
        System.out.println(sb);
    }
    
    private static void solve() {
        int roomNumber = Integer.MAX_VALUE;
        int maxCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = dfs(i, j);
                
                if (count > maxCount) {
                    maxCount = count;
                    roomNumber = room[i][j];
                } else if (count == maxCount) {
                    roomNumber = Math.min(roomNumber, room[i][j]);
                }
            }
        }
        sb.append(roomNumber).append(" ").append(maxCount).append('\n');
    }
    
    private static int dfs(int x, int y) {
        if (memo[x][y] != 0) return memo[x][y];
        
        memo[x][y] = 1;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + DIR[i][0]; int ny = y + DIR[i][1];
            
            if (!isValidRange(nx, ny) || !isValidRoom(x, y, nx, ny)) continue;
            
            memo[x][y] = Math.max(memo[x][y], dfs(nx, ny) + 1);
        }
        
        return memo[x][y];
    }
    
    private static boolean isValidRoom(int x, int y, int nx, int ny) {
        return room[nx][ny] - room[x][y] == 1; 
    }
    
    private static boolean isValidRange(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N; 
    }
    
    private static void init(int tc) throws IOException {
        sb.append("#").append(tc).append(" ");
        N = Integer.parseInt(br.readLine());
        room = new int[N][N]; 
        memo = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                room[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }
    }
}
