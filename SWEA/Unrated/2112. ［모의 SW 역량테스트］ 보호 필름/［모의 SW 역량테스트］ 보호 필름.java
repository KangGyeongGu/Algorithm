import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[] arr;
    static int minChanges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[W];  
            minChanges = K;  

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    arr[j] = (arr[j] << 1) | temp;
                }
            }

            if (check(arr)) {
                sb.append("#").append(tc).append(" 0\n");
            } else {
                dfs(0, 0);
                sb.append("#").append(tc).append(" ").append(minChanges).append("\n");
            }
        }
        System.out.println(sb);
    }

    static boolean check(int[] film) {
        int mask = (1 << K) - 1;

        for (int col = 0; col < W; col++) {
            boolean valid = false;
            for (int row = 0; row <= D - K; row++) {
                int temp = film[col] & (mask << row);
                if (temp == (mask << row) || temp == 0) {
                    valid = true;
                    break;
                }
            }
            if (!valid) return false;
        }
        return true;
    }

    static void dfs(int row, int count) {
        if (count >= minChanges) return;
        if (check(arr)) {
            minChanges = count;
            return;
        }
        if (row >= D) return;  

        int[] original = arr.clone(); 

        for (int j = 0; j < W; j++) arr[j] &= ~(1 << row);
        dfs(row + 1, count + 1);

        for (int j = 0; j < W; j++) arr[j] |= (1 << row);
        dfs(row + 1, count + 1);

        System.arraycopy(original, 0, arr, 0, W);

        dfs(row + 1, count);
    }
}
