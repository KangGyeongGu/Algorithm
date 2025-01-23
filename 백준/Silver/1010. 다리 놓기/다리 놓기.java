import java.util.Scanner;

public class Main {
	
	/* (1) nCr = n-1Cr-1 + n-1Cr
	 * (2) nC0 = nCn = 1
	 * */
	
	static int[][] dp = new int[30][30];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int R = cb(M, N);
			System.out.println(R);
		}
		
	}
	
	/* (1)Memoization할 배열 사이즈 선택 시, 기재된 입력 크기를 정확히 확인해야 한다.
	 * (2) DP + Mem 이용 시, 항상 Mem에 기존 값이 있는 경우 바로 값을 반환해주는 로직이 필요하다.
	 * (3) Memoization할 배열 사이즈 선택 시, 기재된 입력 크기를 정확히 확인해야 한다.
	 * */
	static int cb(int M, int N) {
		if(dp[M][N] > 0) return dp[M][N];
		
		if (M==N || N==0) return dp[M][N] = 1;
		
		return dp[M][N] = cb(M-1, N-1) + cb(M-1, N);
	}
	
}