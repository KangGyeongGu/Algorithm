import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, count;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
        (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]
    );

    public static void main(String[] args) throws IOException {
        init();
        greedy();
        System.out.println(count);
    }

    private static void greedy() {
        int[] cur = pq.poll();
        count = 1;

        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            if (cur[1] <= next[0]) {
                count++;
                cur = next;
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] in = br.readLine().split(" ");
            pq.add(new int[] {Integer.parseInt(in[0]), Integer.parseInt(in[1])});
        }
    }
}
