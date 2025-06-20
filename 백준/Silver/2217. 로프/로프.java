import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] ropes;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ropes = new int[N];

        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void greedy() {
        Arrays.sort(ropes);

        int maxWeight = 0;

        for (int i = 0; i < N; i++) {
            int weight = ropes[i] * (N - i);

            if (weight > maxWeight) {
                maxWeight = weight;
            }
        }

        System.out.println(maxWeight);
    }

    public static void main(String[] args) throws Exception {
        init();
        greedy();
    }
}