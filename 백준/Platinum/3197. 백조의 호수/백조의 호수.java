import java.io.*;
import java.util.*;

public class Main {

    static class Coord {
        int r, c;
        Coord (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] map;
    static boolean[][] isVisited;
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    static List<Coord> swans = new ArrayList<>();

    static Queue<Coord> waterQueue = new ArrayDeque<>();
    static Queue<Coord> swanQueue = new ArrayDeque<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String in = br.readLine();

            for (int c = 0; c < C; c++) {
                char ch = in.charAt(c);

                if (ch == '.') {
                    waterQueue.offer(new Coord(r, c));
                    map[r][c] = ch;
                }

                else if (ch == 'L') {
                    swans.add(new Coord(r, c));
                    waterQueue.add(new Coord(r, c));
                    map[r][c] = '.';
                }

                else map[r][c] = ch;
            }
        }
    }

    private static boolean isSwanCanMeet(Coord end) {
        Queue<Coord> Q = new ArrayDeque<>();

        while (!swanQueue.isEmpty()) {
            Coord curr = swanQueue.poll();

            if (curr.r == end.r && curr.c == end.c) {
                return true;
            }

            for (int[] dir : DIR) {
                int nr = curr.r + dir[0];
                int nc = curr.c + dir[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || isVisited[nr][nc]) continue;

                isVisited[nr][nc] = true;

                if (map[nr][nc] == '.') swanQueue.offer(new Coord(nr, nc));

                else Q.offer(new Coord(nr, nc));
            }
        }

        swanQueue = Q;
        return false;
    }

    private static void meltDownIce() {
        int qSize = waterQueue.size();

        for (int i = 0; i < qSize; i++) {
            Coord curr = waterQueue.poll();

            for (int[] dir : DIR) {
                int nr = curr.r + dir[0];
                int nc = curr.c + dir[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                if (map[nr][nc] == 'X') {
                    map[nr][nc] = '.';
                    waterQueue.offer(new Coord(nr, nc));
                }
            }
        }
    }

    private static void run() {
        Coord start = swans.get(0);
        Coord end = swans.get(1);

        swanQueue.offer(start);
        isVisited[start.r][start.c] = true;

        int days = 0;

        while (true) {

            if (isSwanCanMeet(end)) {
                System.out.println(days);
                return;
            }

            meltDownIce();
            days++;
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
