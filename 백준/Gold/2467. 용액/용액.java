import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] fluid;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        fluid = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fluid[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void twoPointer() {

        int left = 0, right = N-1;
        int fluid1 = 0, fluid2 = 0;
        int optimalSum = Integer.MAX_VALUE;

        while (left < right) {
            int currentSum = fluid[left] + fluid[right];

            if (Math.abs(currentSum) < Math.abs(optimalSum)) {
                optimalSum = currentSum;
                fluid1 = fluid[left];
                fluid2 = fluid[right];
            }

            if (currentSum < 0) {
                left++;
            } else if (currentSum > 0) {
                right--;
            } else {
                System.out.println(fluid1 + " " + fluid2);
                return;
            }
        }

        System.out.println(fluid1 + " " + fluid2);
    }

    public static void main(String[] args) throws Exception {
        init();
        twoPointer();
    }
}