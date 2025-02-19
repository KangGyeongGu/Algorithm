import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static final int[][] DIR = { {0,1}, {1,0}, {0,-1}, {-1,0} };
	
	static int T, N, tN;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			makeArr();
			printer();
		}
		System.out.println(sb);
		br.close();
	}
	
	private static void printer() { // save each test cases result.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(arr[r][c]).append(" ");
			}sb.append('\n');
		}
	}
	
	private static void makeArr() {
		int r = 0, c = 0, dir = 0;
		
		for (int i = 1; i <= tN; i++) { // loop for 1 ~ N*N sequence numbers.
			arr[r][c] = i;
			
			int nr = r + DIR[dir][0]; int nc = c + DIR[dir][1];
			
			if (!isValidPos(nr, nc)) { // should switch direction?
				dir = (dir+1) % 4; // switch direction
				nr = r + DIR[dir][0]; nc = c + DIR[dir][1]; // update switched direction (arrays' next index)
			}
			
			r = nr; c = nc; // just update next index
		}
	}
	
	private static boolean isValidPos(int nr, int nc) { // check valid position
		return 0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0; // check 'arr[nr][nc] == 0' instead using isVisited array.
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append("\n");
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N]; tN = N*N;
	}
}