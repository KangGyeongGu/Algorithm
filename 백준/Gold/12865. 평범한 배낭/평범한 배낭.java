import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, K;
	static int[] W, V, dp;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N+1]; V = new int[N+1];
		dp = new int[K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 최종 무게 K부터 W[i]까지 역순 진행 > 같은 물건을 중복으로 사용하면 안되기 때문에,
		 * 
		 * W[i], V[i] : i번째 물건의 무게와 가치
		 * dp[j] : 배낭 무게가 j일때 현재 가능한 최대 가치 (갱신 가능)
		 * */
		for (int i = 1; i <= N; i++) { // 1번 ~ N번 물건 (N개) 순회하기
			for (int j = K; j >= W[i]; j--) { // i번째 물건의 무게로 가능한 배낭무게 최대 가치 탐색하기
				dp[j] = Math.max(dp[j], dp[j-W[i]] + V[i]);
			}
		}
		
		System.out.println(dp[K]);
	}
}


// input
//4 7
//6 13
//4 8
//3 6
//5 12

//logic
//i:1, j:7 >> [0, 0, 0, 0, 0, 0, 0, 13]
//i:1, j:6 >> [0, 0, 0, 0, 0, 0, 13, 13]
//i:2, j:7 >> [0, 0, 0, 0, 0, 0, 13, 13]
//i:2, j:6 >> [0, 0, 0, 0, 0, 0, 13, 13]
//i:2, j:5 >> [0, 0, 0, 0, 0, 8, 13, 13]
//i:2, j:4 >> [0, 0, 0, 0, 8, 8, 13, 13]
//i:3, j:7 >> [0, 0, 0, 0, 8, 8, 13, 14]
//i:3, j:6 >> [0, 0, 0, 0, 8, 8, 13, 14]
//i:3, j:5 >> [0, 0, 0, 0, 8, 8, 13, 14]
//i:3, j:4 >> [0, 0, 0, 0, 8, 8, 13, 14]
//i:3, j:3 >> [0, 0, 0, 6, 8, 8, 13, 14]
//i:4, j:7 >> [0, 0, 0, 6, 8, 8, 13, 14]
//i:4, j:6 >> [0, 0, 0, 6, 8, 8, 13, 14]
//i:4, j:5 >> [0, 0, 0, 6, 8, 12, 13, 14]