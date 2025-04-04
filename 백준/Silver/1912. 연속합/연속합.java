import java.util.*;
import java.io.*;

/*
 * i : arr 배열의 각 인덱스 위치
 * D[i] : 해당 arr 인덱스 위치에서 부분수열 합 최댓값
 * D[i] = max(D[i-1] + arr[i], arr[i])
 * 		>> 현재 인덱스 위치 에서 부분수열 최댓값
 * 			>>> (이전 인덱스 부분 수열의 최대 값 + 현재 값) ? 현재 값
 * */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			dp[i] = arr[i];
			
			if(i > 0) dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
		}
		
		int ans = Integer.MIN_VALUE;
		for (int elem : dp) ans = Math.max(ans, elem);
		System.out.println(ans);
	}
}
