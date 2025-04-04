import java.util.*;
import java.io.*;

/*
 * DP[] 배열
 *  > 현재 값 i 를 기준으로,
 *  	> 이전 값 0 ~ i-1 까지의 D[i-1] 값들은 모두 최적해이므로,
 *  		> 해당 이전 값(들)을 이용하여 D[i] 값을 갱신하려고 할 때,
 *  			> 갱신되는 값이 최적값이 되는지를 찾아내면 되고,
 *  				> 최적값인지 판별(찾아내는) 로직 == 점화식
 * */

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int ans = 0;
        
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            
            for (int j = 0; j < i; j++) {
            	if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
