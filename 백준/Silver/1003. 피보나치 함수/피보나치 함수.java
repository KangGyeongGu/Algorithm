import java.util.Scanner;

public class Main {
	static Integer[][] dp = new Integer[41][2];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();
			Integer[] R = fib(N);
			System.out.println(R[0] + " " + R[1]);
		}
		
	}
	
	static Integer[] fib(int N) {
		if (dp[N][0] == null || dp[N][1] == null) {
			dp[N][0] = fib(N-1)[0] + fib(N-2)[0];
			dp[N][1] = fib(N-1)[1] + fib(N-2)[1];
		}
		return dp[N];
	}
}