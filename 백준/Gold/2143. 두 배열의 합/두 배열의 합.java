import java.io.*;
import java.util.*;

public class Main {

    static int T, N, M;
    static int[] A, B;
    static List<Long> sumA = new ArrayList<>();
    static Map<Long, Long> sumB = new HashMap<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            long sum = 0;

            for (int j = i; j < N; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        for (int i = 0; i < M; i++) {
            long sum = 0;

            for (int j = i; j < M; j++) {
                sum += B[j];
                sumB.put(sum, sumB.getOrDefault(sum, 0L) + 1);
            }
        }

        long answer = 0;
        for (long a : sumA) {
            long b = T - a;

            if (sumB.containsKey(b)) {
                answer += sumB.get(b);
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        init();
        solve();
    }
}
