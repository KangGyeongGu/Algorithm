import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] solutions, result;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        solutions = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }
    }

    private static void binarySearch() {
        Arrays.sort(solutions);

        long minSum = Long.MAX_VALUE;
        result = new long[3];

        for (int i = 0; i < N-2; i++) {
            int left = i+1, right = N-1;

            while (left < right) {
                long sum = solutions[i] + solutions[left] + solutions[right];

                if (Math.abs(sum) < Math.abs(minSum)) {
                    minSum = sum;
                    result[0] = solutions[i];
                    result[1] = solutions[left];
                    result[2] = solutions[right];
                }

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    break;
                }
            }

            if (minSum == 0) break;
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    public static void main(String[] args) throws Exception {
        init();
        binarySearch();
    }
}
