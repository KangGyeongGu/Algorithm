import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, count;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        greedy();
        System.out.println(count);
    }

    private static void greedy() {
        int[] cur = list.get(0); count = 1;
        
        for (int i = 1; i < list.size(); i++) {
            int[] next = list.get(i);
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
            list.add(new int[]{Integer.parseInt(in[0]), Integer.parseInt(in[1])});
        }
        Collections.sort(list, (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]);
    }
}
