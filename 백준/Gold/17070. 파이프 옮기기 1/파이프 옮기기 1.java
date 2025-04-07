import java.io.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while((c=System.in.read()) >= 48) n = (n<<3) + (n<<1) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		
		int N = read(), home[][] = new int[N][N];
		for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) home[i][j] = read();
		

		int[][][] dp = new int[N][N][3];

		dp[0][1][0] = 1;
		
		for (int x = 0; x < N; x++) {
		    for (int y = 0; y < N; y++) {
		        if (home[x][y] == 1) continue;

		        // 가로
		        if (y - 1 >= 0 && home[x][y - 1] == 0) {
		            dp[x][y][0] += dp[x][y - 1][0];
		            dp[x][y][0] += dp[x][y - 1][2];
		        }

		        // 세로
		        if (x - 1 >= 0 && home[x - 1][y] == 0) {
		            dp[x][y][1] += dp[x - 1][y][1];
		            dp[x][y][1] += dp[x - 1][y][2];
		        }

		        // 대각
		        if (x - 1 >= 0 && y - 1 >= 0 && home[x - 1][y] == 0 && home[x][y - 1] == 0 && home[x - 1][y - 1] == 0) {
		            dp[x][y][2] += dp[x - 1][y - 1][0];
		            dp[x][y][2] += dp[x - 1][y - 1][1];
		            dp[x][y][2] += dp[x - 1][y - 1][2];
		        }
		    }
		}

		int result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		bw.write(result + "\n"); bw.flush(); bw.close();
	}
}
