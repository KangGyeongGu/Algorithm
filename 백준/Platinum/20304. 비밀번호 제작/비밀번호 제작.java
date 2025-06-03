import java.io.*;
import java.util.*;

public class Main {

    static int N, M, maxBits, maxSafety;
    static int[] safety;
    static Queue<Integer> Q = new ArrayDeque<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        safety = new int[N+1];
        Arrays.fill(safety, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            int tried = Integer.parseInt(st.nextToken());
            safety[tried] = 0;
            Q.offer(tried);
        }

        maxBits = Integer.toBinaryString(N).length();
        maxSafety = 0;
    }

    private static void bfs() {
        while (!Q.isEmpty()) {
            int curr = Q.poll();

            for (int bit = 0; bit < maxBits; bit++) {
                int next = curr ^ (1 << bit);

                if (next <= N && safety[next] == -1) {
                    safety[next] = safety[curr] + 1;
                    maxSafety = Math.max(maxSafety, safety[next]);
                    Q.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        bfs();
        System.out.println(maxSafety);
    }
}