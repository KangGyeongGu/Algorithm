import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int R, C, pipeLine;
	static char[][] pipe;
	static boolean[][] iv;
	
	static final int[][] DIR = { {-1,1}, {0,1}, {1,1} };
	static final char BUILDING = 'x';
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	private static void run() {
		for (int r = 0; r < R; r++) {
			iv[r][0] = true;
			if (dfs(r, 0)) pipeLine++;
		}
		System.out.println(pipeLine);
	}
	
	private static boolean dfs(int x, int y) {
		if (y == C-1) return true; // pipeline setting complete,
		
		for (int[] dir : DIR) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			
			if (!isValidPath(nx, ny)) continue;
			
			iv[nx][ny] = true;
			
			if (dfs(nx, ny)) return true; // once the pipeline set, never search another case, return true immediately,
		}
		
		return false;
	}
	
	private static boolean isValidPath(int nx, int ny) {
		return 0 <= nx && nx < R && 0 <= ny && ny < C && !iv[nx][ny] && pipe[nx][ny] != BUILDING;
	}
	
	private static void init() throws IOException {
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]); C = Integer.parseInt(RC[1]);
		
		pipe = new char[R][C]; iv = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String in = br.readLine();
			for (int c = 0; c < C; c++) {
				pipe[r][c] = in.charAt(c);
			}
		}
		br.close();
	}
}
