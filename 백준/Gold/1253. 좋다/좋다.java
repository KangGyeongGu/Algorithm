import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void binarySearch() {
        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < N; i++) {
            int target = arr[i];
            int left = 0, right = N-1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }

                if (right == i) {
                    right--;
                    continue;
                }

                int sum = arr[left] + arr[right];

                if (sum == target) {
                    count++;
                    break;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        init();
        binarySearch();
    }
}