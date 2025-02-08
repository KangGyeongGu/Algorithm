import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        while (K > 0) {
            for (int i = N-1; i >= 0; i--) {
                if (C[i] <= K) {
                    K -= C[i];
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);

        br.close();
    }
}