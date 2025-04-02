import java.io.*;
import java.util.*;

public class Main {
    static int N, D, K, C, sushi[], ate[];

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sushi = new int[N];
        ate = new int[D + 1];

        for (int n = 0; n < N; n++) sushi[n] = Integer.parseInt(br.readLine());
    }

    private static void slidingWindow() {
        int count = 0;

        for (int i = 0; i < K; i++) {
            if (ate[sushi[i]] == 0) count++;
            ate[sushi[i]]++;
        }

        int max = count;
        if (ate[C] == 0) max++;

        int start = 0, end = K;

        while (start < N) {
            if (--ate[sushi[start]] == 0) count--;

            if (ate[sushi[end % N]]++ == 0) count++;

            if (ate[C] == 0) max = Math.max(max, count + 1);
            else max = Math.max(max, count);

            start++; end++;
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        init();
        slidingWindow();
    }
}
