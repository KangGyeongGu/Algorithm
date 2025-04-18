import java.io.*;

public class Main {

    static final int MOD = 1_000_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10];

        // 초기값 설정: 길이가 1일 때는 1~9만 가능
        for (int i = 1; i <= 9; i++) dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) dp[i][j] += dp[i - 1][j - 1];
                if (j < 9) dp[i][j] += dp[i - 1][j + 1];
                dp[i][j] %= MOD;
            }
        }

        long answer = 0;
        for (int j = 0; j <= 9; j++) answer = (answer + dp[N][j]) % MOD;

        System.out.println(answer);
    }
}
