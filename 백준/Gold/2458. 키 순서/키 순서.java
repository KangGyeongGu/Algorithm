import java.util.*;
import java.io.*;

public class Main {

    static int N, M, ANS;
    static boolean[][] dist;

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    private static void init() throws Exception {
        N = read();
        M = read();
        dist = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            dist[a][b] = true; // a → b (a가 b보다 키가 작음)
        }
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) { // 경유점
            for (int i = 1; i <= N; i++) { // 출발점
                for (int j = 1; j <= N; j++) { // 도착점
                    if (dist[i][k] && dist[k][j]) {
                        dist[i][j] = true;
                    }
                }
            }
        }
    }

    private static void countKnownOrders() {
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] || dist[j][i]) count++; // 자신보다 크거나 작은 학생 수
            }
            if (count == N - 1) ANS++; // 자기 순서를 정확히 알 수 있는 경우
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        floydWarshall();
        countKnownOrders();
        System.out.println(ANS);
    }
}
