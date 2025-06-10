import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] S, G;

    static StringBuilder sb = new StringBuilder();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            S[n] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        G = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            G[m] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(S);
    }

    private static boolean binarySearch(int target) {
        int left = 0, right = S.length-1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (S[mid] == target) {
                return true;
            } else if (S[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    private static void run() {
        for (int m = 0; m < M; m++) {
            if (binarySearch(G[m])) {
                sb.append("1").append(" ");
            } else {
                sb.append("0").append(" ");
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
