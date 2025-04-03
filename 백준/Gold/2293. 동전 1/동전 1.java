import java.util.*;
import java.io.*;

/* DP + memoization
 * (1) 점화식 : dp[j] = dp[j - arr[i]]
 * (2) 초기값 : dp[0] = 1
 * 		- 금액 0원을 만드는 동전 조합 수는 동전을 내지 않는 경우 1로 초기화
 * 		- 첫 동전을 추가하는 상태에서, 이전 상태인 "아무 동전도 추가하지 않은 상태, dp[0]"을 참조하므로 1로 초기화
 * */

public class Main {

	static int N, K, coins[], cost[];
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());
	}
	
	private static void dp() {
		cost = new int[K+1];
		cost[0] = 1;
		for (int i = 0; i < N; i++) { // 중복 제거
			for (int j = coins[i]; j <= K; j++) {
				cost[j] += cost[j-coins[i]];
			}
		}
		System.out.println(cost[K]);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
	}
}
