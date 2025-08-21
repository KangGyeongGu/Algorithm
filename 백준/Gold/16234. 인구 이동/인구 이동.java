import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean bfs(int stx, int etx) {
        Queue<Pos> Q = new ArrayDeque<>();
        List<Pos> union = new ArrayList<>();

        Q.add(new Pos(stx, etx));
        union.add(new Pos(stx, etx));

        visited[stx][etx] = true;
        int sum = map[stx][etx];

        while (!Q.isEmpty()) {
            Pos curr = Q.poll();
            int cx = curr.x, cy = curr.y;

            for (int[] dir : DIR) {
                int nx = cx + dir[0], ny = cy + dir[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                int diff = Math.abs(map[cx][cy] - map[nx][ny]);

                if (diff >= L && diff <= R) {
                    Q.add(new Pos(nx, ny));
                    union.add(new Pos(nx, ny));
                    visited[nx][ny] = true;
                    sum += map[nx][ny];
                }
            }
        }

        if (union.size() > 1) {
            int avg = sum / union.size();
            for (Pos cell : union) {
                map[cell.x][cell.y] = avg;
            }
            return true;
        }
        return false;
    }

    private static void run() {
        int days = 0;
        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i,j)) moved = true;
                    }
                }
            }

            if (!moved) break;
            days++;
        }

        System.out.println(days);
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
