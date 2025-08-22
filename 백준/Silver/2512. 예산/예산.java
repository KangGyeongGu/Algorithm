import java.io.*;
import java.util.*;

public class Main {

    static int N, M, maxRequest;
    static int[] requests;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        requests = new int[N];
        maxRequest = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, requests[i]);
        }

        M = Integer.parseInt(br.readLine());
    }

    private static void bSearch() {
        int left = 0, right = maxRequest;

        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = 0;

            for (int request : requests) {
                sum += Math.min(request, mid);
            }

            if (sum <= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    public static void main(String[] args) throws Exception {
        init();
        bSearch();
    }
}
