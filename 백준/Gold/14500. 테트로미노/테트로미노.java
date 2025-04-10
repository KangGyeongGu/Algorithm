import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<1) + (n<<3) + (c&15);
		if (c==13) System.in.read();
		return n;
	}

	static int N, M, map[][], ANS;
	static boolean[][] iv;
	
	static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0} };
	
	private static void init() throws Exception {
		N = read(); M = read();
		
		ANS = Integer.MIN_VALUE;
		iv = new boolean[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = read();
			}
		}
	}
	
	private static void dfs(int depth, int x, int y, int sum) {
		if (depth == 4) {
			ANS = Math.max(ANS, sum);
			return;
		}
		
		for (int[] dir : DIR) {
			int nx = x + dir[0], ny = y + dir[1];
			
			if (N <= nx || nx < 0 || M <= ny || ny < 0 || iv[nx][ny]) continue;
			
			if (depth == 2) {
				iv[nx][ny] = true;
				dfs(depth+1, x, y, sum + map[nx][ny]);
				iv[nx][ny] = false;
			}
			
			iv[nx][ny] = true;
			dfs(depth+1, nx, ny, sum + map[nx][ny]);
			iv[nx][ny] = false;
		}
	}
	
	private static void simulation() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				iv[i][j] = true;
				dfs(1, i, j, map[i][j]);
				iv[i][j] = false;
			}
		}
		System.out.println(ANS);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		simulation();
	}
}