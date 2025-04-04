import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		int[] arr = new int[N];
		int ans = Integer.MIN_VALUE; 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			dp[i] = arr[i];
			
			if (i > 0) for (int j = 0; j < i; j++) if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j]+arr[i]);
			
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
