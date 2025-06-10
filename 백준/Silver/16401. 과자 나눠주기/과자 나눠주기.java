import java.io.*;
import java.util.*;

public class Main {

    static int N, M, maxLength;
    static int[] snacks;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        snacks = new int[N];
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            maxLength = Math.max(maxLength, snacks[i]);
        }
    }

    private static boolean distributable(int length) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            count += snacks[i] / length;
        }

        return count >= M;
    }

    private static void run() {
        int left = 1, right = maxLength, answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (distributable(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
