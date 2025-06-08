import java.io.*;
import java.util.*;

public class Main {

    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coord)) return false;
            Coord coord = (Coord) o;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int N, M, answer, cheeseCount;
    static int[][] map;

    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());

                if (map[n][m] == 1) cheeseCount++;
            }
        }
    }

    private static boolean meltDownCheeseBfs() {
        Queue<Coord> Q = new ArrayDeque<>();
        boolean[][] iv = new boolean[N][M];

        Q.offer(new Coord(0, 0));
        iv[0][0] = true;

        Map<Coord, Integer> cheese = new HashMap<>();

        while (!Q.isEmpty()) {
            Coord cur = Q.poll();

            for (int[] dir : DIR) {
                int nx = cur.x + dir[0], ny = cur.y + dir[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || iv[nx][ny]) continue;

                Coord next = new Coord(nx, ny);

                if (map[nx][ny] == 1) {
                    cheese.put(next, cheese.getOrDefault(next, 0) + 1);
                } else {
                    iv[nx][ny] = true;
                    Q.offer(new Coord(nx, ny));
                }

            }
        }

        for (Map.Entry<Coord, Integer> entry : cheese.entrySet()) {
            if (entry.getValue() >= 2) {
                Coord cur = entry.getKey();
                map[cur.x][cur.y] = 0;
                cheeseCount--;
            }
        }

        answer++;

        return cheeseCount == 0;
    }

    private static void run() {
        while (true) {
            if (meltDownCheeseBfs()) {
                System.out.println(answer);
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
