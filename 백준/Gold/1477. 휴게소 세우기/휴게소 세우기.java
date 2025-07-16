import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L;
    static int[] position;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        position = new int[N+2];
        position[0] = 0;
        position[N+1] = L;

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                position[i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean canBuild(int maxDistance) {
        int count = 0;

        for (int i = 1; i < position.length; i++) {
            int distance = position[i] - position[i - 1];

            if (distance > maxDistance) {
                count += (distance - 1) / maxDistance;
            }
        }

        return count <= M;
    }

    private static void binarySearch() {
        Arrays.sort(position);

        int left = 1, right = L, answer = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canBuild(mid)) {
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
        binarySearch();
    }
}