import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] U;
    static List<Integer> prefix = new ArrayList<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        U = new int[N];

        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(U);
    }

    private static void prefixSum() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                prefix.add(U[i] + U[j]);
            }
        }

        Collections.sort(prefix);
    }

    private static void findMax() {
        for (int k = N-1; k >= 0; k--) {
            for (int z = 0; z < N; z++) {
                int target = U[k] - U[z]; // x + y = k - z

                if (Collections.binarySearch(prefix, target) >= 0) {
                    System.out.println(U[k]);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        prefixSum();
        findMax();
    }
}
