import java.io.*;
import java.util.*;

/*
 * 1. DP : bottom-up + memo
 * 
 * 2. 부분 문제 식별
 * 	- i : 주어진 정수 값
 *  - D[i] : i 값을 1로 만드는 연산의 최소 횟수
 * D[0] = 연산 없음 = 0
 * D[1] = 연산 없음 = 0
 * D[2] = x-1 || x/2 = 1
 * D[3] = x/3 = 1
 * D[4] = x/2/2 || x/3-1 = 2
 * D[5] = x/2/2-1 || x/3-1-1 = 3
 * 
 * 3. 점화식
 * f(n) = min(f(n-1)+1, f(n/3)+1, f(n/2)+1)
 * 
 * 4. 초기 조건
 * dp[0] = 0, dp[1] = 0
 * 
 * */

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		int[] trace = new int[N+1];
		
		dp[0] = dp[1] = 0;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + 1; // N 값에서 N-1 값이 되는 방법과 동일함 == N-1의 최소 연산 수 + 1회 == N의 최소 연산 
			trace[i] = i-1;
			if (i % 2 == 0 && dp[i] > dp[i/2]+1) {
				dp[i] = dp[i/2]+1; // x-1 보다 /2 가 더 적은 연산 횟수를 반환하는 경우에만 업데이
				trace[i] = i/2;
			}
			if (i % 3 == 0 && dp[i] > dp[i/3]+1) { // x-1 보다 /3 가 더 적은 연산 횟수를 반환하는 경우에만 업데이
				dp[i] = dp[i/3]+1; 
				trace[i] = i/3;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N]).append('\n');
		
		int cur = N; 
		for (int elem : trace) {
			sb.append(cur).append(" ");
			if (cur == 1) break;
			cur = trace[cur];
		}
		
		System.out.println(sb);
	}
}
