import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dist;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i==j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            dist[a][b] = 1;
            dist[b][a] = 1;
        }
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    private static void run() {
        int minScore = Integer.MAX_VALUE;
        int[] scores = new int[N+1];

        for (int i = 1; i <= N; i++) {
            int maxDist = 0;

            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }

            scores[i] = maxDist;
            minScore = Math.min(minScore, maxDist);
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (scores[i] == minScore) {
                count++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(minScore + " " + count);
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        init();
        floyd();
        run();
    }
}