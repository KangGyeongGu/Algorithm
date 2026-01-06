import java.util.*;
import java.io.*;

class Pos {
    int x;
    int y;
    int dist;

    Pos(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {

    private static final int[][] DIR = { {0,-1}, {-1,0}, {1,0}, {0,1} };
    private static int N;
    private static int[][] map;
    private static Pos startPos;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(run());
    }

    private static int run() {
        int time = 0;
        int size = 2;
        int eaten = 0;

        Pos curr = startPos;

        while (true) {
            Pos target = bfs(curr, size);
            if (target == null) break;

            time += target.dist;
            eaten++;

            if (eaten == size) {
                size++;
                eaten = 0;
            }

            map[target.x][target.y] = 0;
            curr = new Pos(target.x, target.y, 0);
        }

        return time;
    }

    private static Pos bfs(Pos start, int size) {
        boolean[][] visited = new boolean[N][N];
        Queue<Pos> dq = new ArrayDeque<>();
        List<Pos> fishes = new ArrayList<>();

        dq.offer(new Pos(start.x, start.y, 0));
        visited[start.x][start.y] = true;

        int minDist = Integer.MAX_VALUE;

        while (!dq.isEmpty()) {
            Pos curr = dq.poll();

            for (int[] dir : DIR) {
                int nx = curr.x + dir[0];
                int ny = curr.y + dir[1];

                if ( nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] > size) continue;

                visited[nx][ny] = true;
                int nd = curr.dist+1;

                if (map[nx][ny] != 0 && map[nx][ny] < size) {
                    fishes.add(new Pos(nx, ny, nd));
                    minDist = nd;
                }

                dq.offer(new Pos(nx, ny, nd));
            }
        }

        if (fishes.isEmpty()) return null;

        fishes.sort((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.x != b.x) return a.x - b.x;
            return a.y - b.y;
        });

        return fishes.get(0);
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startPos = new Pos(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }
    }
}