public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		
		int N = read(), T[] = new int[N+2], P[] = new int[N+2], dp[] = new int[N+2];

		for (int i = 1; i <= N; i++) {
			T[i] = read();
			P[i] = read();
		}
		
		for (int i = 1; i <= N+1; i++) {
			
			// 이전까지의 최대 값으로 먼저 갱신
			dp[i] = Math.max(dp[i], dp[i-1]);
			
			// 현재 날짜에 상담 진행 시, 상담 종료일의 금액 갱신
			if (i + T[i] - 1 <= N) dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			
		}
		
		System.out.println(dp[N+1]);
	}
}
