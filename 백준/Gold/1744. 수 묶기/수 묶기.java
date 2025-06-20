import java.io.*;
import java.util.*;

public class Main {

    static int N, ones, zeros;
    static List<Integer> positive, negative;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        positive = new ArrayList<>();
        negative = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 1) {
                positive.add(num);
            } else if (num == 1) {
                ones++;
            } else if (num == 0) {
                zeros++;
            } else {
                negative.add(num);
            }
        }
    }

    private static void greedy() {
        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int sum = 0;

        for (int i = 0; i < positive.size(); i+=2) {
            if (i+1 < positive.size()) {
                sum += positive.get(i) * positive.get(i+1);
            } else {
                sum += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i+=2) {
            if (i+1 < negative.size()) {
                sum += negative.get(i) * negative.get(i+1);
            } else {
                if (zeros == 0) {
                    sum += negative.get(i);
                }
            }
        }

        sum += ones;

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        init();
        greedy();
    }
}