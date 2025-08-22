import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
    }

    private static long countLessOrEqual(long x) {
        long count = 0;

        for (int i = 1; i <= N; i++) {
            count += Math.min(x/i, N);
        }

        return count;
    }

    private static void bSearch() {
        long left = 1, right = (long)N*N, answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = countLessOrEqual(mid);

            if (count >= K) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        init();
        bSearch();
    }
}
