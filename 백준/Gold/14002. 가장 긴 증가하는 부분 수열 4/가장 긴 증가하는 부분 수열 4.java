import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] prev = new int[N]; 

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;     
            prev[i] = -1;     // 초기값 (이전 없음)
        }

        int max = 0;          // LIS 길이
        int lastIndex = 0;    // LIS 마지막 인덱스

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if (dp[i] > max) {
                max = dp[i];
                lastIndex = i;
            }
        }

        // LIS 경로 복원
        StringBuilder sb = new StringBuilder();

        while (lastIndex != -1) {
            sb.insert(0, arr[lastIndex] + " ");
            lastIndex = prev[lastIndex];
        }

        System.out.println(max);
        System.out.println(sb.toString());
    }
}
