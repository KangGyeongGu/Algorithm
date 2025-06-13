import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map, dp;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M];
    }

    private static void dp() {
        dp[0][0] = map[0][0];

        // 1st row
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        // from 2nd row
        for (int i = 1; i < N; i++) {
            int[] leftToRight = new int[M];
            int[] rightToLeft = new int[M];

            // left -> right
            leftToRight[0] = dp[i-1][0] + map[i][0];
            for (int j = 1; j < M; j++) {
                leftToRight[j] = Math.max(
                        dp[i-1][j] + map[i][j], // came from Up,
                        leftToRight[j-1] + map[i][j] // came from Left,
                );
            }

            // right -> left
            rightToLeft[M-1] = dp[i-1][M-1] + map[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                rightToLeft[j] = Math.max(
                        dp[i-1][j] + map[i][j], // came from Up,
                        rightToLeft[j+1] + map[i][j] // came from right,
                );
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]); // Update into max val,
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        dp();
        System.out.println(dp[N-1][M-1]);
    }
}