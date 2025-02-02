import java.io.*;

public class Main {

    static int[][] apt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            apt = new int[k+1][n+1];
            for (int i = 1; i <= n; i++) apt[0][i] = i;
            for (int i = 1; i <= k; i++) apt[i][1] = 1;

            System.out.println(dp(k, n));
        }
        br.close();
    }

    static int dp(int k, int n) {
        if (apt[k][n] != 0) return apt[k][n];
        else return apt[k][n] = dp(k-1, n) + dp(k, n-1);
    }
}