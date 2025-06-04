import java.io.*;
import java.util.*;

public class Main {

    static class Coord {
        int x, y, hour;

        public Coord(int x, int y, int hour) {
            this.x = x;
            this.y = y;
            this.hour = hour;
        }
    }

    static int N, M, answer;
    static char[][] map;
    static boolean[][] isVisited;
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().toCharArray();
        }

        answer = Integer.MIN_VALUE;
    }

    private static int bfs(Coord start) {
        isVisited = new boolean[N][M];
        isVisited[start.x][start.y] = true;

        Queue<Coord> Q = new ArrayDeque<>();
        Q.offer(start);

        int currentMaxDistance = 0;

        while (!Q.isEmpty()) {
            Coord curr = Q.poll();
            currentMaxDistance = Math.max(currentMaxDistance, curr.hour);

            for (int[] dir : DIR) {
                int nx = curr.x + dir[0], ny = curr.y + dir[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || isVisited[nx][ny] || map[nx][ny] == 'W') continue;

                isVisited[nx][ny] = true;
                Q.offer(new Coord(nx, ny, curr.hour + 1));
            }
        }

        return currentMaxDistance;
    }

    private static void run() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == 'L') {
                    answer = Math.max(bfs(new Coord(n,m,0)), answer);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
        System.out.println(answer);
    }
}