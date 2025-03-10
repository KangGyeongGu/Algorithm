import java.io.*;
import java.util.*;

public class Main {
    
    static class Enemy {
        int x, y, dist;
        Enemy(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int N, M, D, maxKill = 0;
    static int[][] origin, temp;
    static int[] archers = new int[3];
    
    public static void main(String[] args) throws IOException {
        init();
        combination(0, 0); 
        System.out.println(maxKill);
    }
    
    private static void combination(int depth, int start) {
        if (depth == 3) {
            simulate(); 
            return;
        }

        for (int i = start; i < M; i++) {
            archers[depth] = i;
            combination(depth + 1, i + 1);
        }
    }

    private static void simulate() {
        temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = origin[i].clone();
        }

        int totalKill = 0;
        
        for (int turn = 0; turn < N; turn++) {
            Set<Enemy> targets = new HashSet<>();

            for (int y : archers) {
                Enemy target = chooseEnemy(y);
                if (target != null) targets.add(target);
            }

            for (Enemy e : targets) {
                if (temp[e.x][e.y] == 1) {
                    temp[e.x][e.y] = 0;
                    totalKill++;
                }
            }

            moveEnemies();
        }

        maxKill = Math.max(maxKill, totalKill);
    }

    private static Enemy chooseEnemy(int archerY) {
        PriorityQueue<Enemy> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist; 
            return a.y - b.y; 
        });

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 1) {
                    int distance = Math.abs(N - i) + Math.abs(archerY - j);
                    if (distance <= D) {
                        pq.offer(new Enemy(i, j, distance));
                    }
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    private static void moveEnemies() {
        for (int i = N - 1; i > 0; i--) {
            temp[i] = temp[i - 1].clone();
        }
        Arrays.fill(temp[0], 0);
    }
    
    private static void init() throws IOException {
   	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }
   }
}
