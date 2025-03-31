import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ANS;
    static int[][] cost, dp;
    static final int INF = 1_000_000; 

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ANS = INF;

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            dp = new int[N][3];

            for (int j = 0; j < 3; j++) {
                if (j == firstColor) dp[0][j] = cost[0][j];
                else dp[0][j] = INF; // 나머지는 고를 수 없게 처리
            }

            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
            }

            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    ANS = Math.min(ANS, dp[N - 1][lastColor]);
                }
            }
        }

        System.out.println(ANS);
    }
}
