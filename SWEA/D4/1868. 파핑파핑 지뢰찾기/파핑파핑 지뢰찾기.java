import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N;
    static char[][] map;
    static boolean[][] visited;

    static Queue<Node> Q = new ArrayDeque<>();
    static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1,1}, {1,1}, {1,-1} };
    static final char MINE = '*', SAFE = '.';

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            int result = solve();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve() {
        int clickCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { // find 8-DIR SAFE area and click first,
                if (map[i][j] == SAFE && !visited[i][j] && getMineCount(i, j) == 0) {
                    bfs(i, j);
                    clickCount++;
                }
            }
        }

        for (int i = 0; i < N; i++) { // find rest SAFE area (this area might contains MINE)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == SAFE && !visited[i][j]) {
                    clickCount++;
                }
            }
        }

        return clickCount;
    }

    private static void bfs(int x, int y) {
        Q.clear();
        Q.offer(new Node(x, y));
        visited[x][y] = true;

        while (!Q.isEmpty()) {
            Node cur = Q.poll();
            int cx = cur.x, cy = cur.y;
            int mineCount = getMineCount(cx, cy);
            map[cx][cy] = (char) (mineCount + '0'); // update current area's count,

            if (mineCount == 0) { // continusly polled area must be SAFE (that means, the current area's count is 0
                for (int[] dir : DIR) {
                    int nx = cx + dir[0], ny = cy + dir[1];

                    if (isValidMove(nx, ny) && !visited[nx][ny] && map[nx][ny] == SAFE) {
                        visited[nx][ny] = true;
                        Q.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }

    private static int getMineCount(int x, int y) {
        int count = 0;
        for (int[] dir : DIR) {
            int nx = x + dir[0], ny = y + dir[1];
            if (isValidMove(nx, ny) && map[nx][ny] == MINE) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String in = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = in.charAt(j);
            }
        }
    }
}