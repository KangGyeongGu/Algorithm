import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int[][] DIR = {{0,1}, {0,-1}, {1,0}, {-1,0} };
	
	static int N, maxDust, minDust, answer;
	static int[][] map;
	static boolean[][] iV;
		
	public static void main(String[] args) throws IOException {
		init();
		simulation();
		System.out.println(answer);
	}
	
	private static void simulation() {
		for (int i = 0; i < maxDust; i++) {
			answer = Math.max(answer, run(i));
		}
	}
	
	private static int run(int upperBound) {
		int noc = 0;
		iV = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] <= upperBound || iV[r][c]) continue;
				iV[r][c] = true;
				bfs(r,c, upperBound);
				noc++;
			}
		}
		return noc;
	}
	
	private static void bfs(int startx, int starty, int upperBound) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startx, starty});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0]; int cy = cur[1];
			
			for (int[] dir : DIR) {
				int nx = cx + dir[0]; int ny = cy + dir[1];
				
				if (!isValidPosition(nx, ny, upperBound)) continue;
				
				iV[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	private static boolean isValidPosition(int nx, int ny, int upperBound) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N && !iV[nx][ny] && map[nx][ny] > upperBound;
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		answer = Integer.MIN_VALUE; maxDust = Integer.MIN_VALUE; minDust = Integer.MAX_VALUE;
		
		iV = new boolean[N][N];
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int in = Integer.parseInt(st.nextToken());
				map[r][c] = in;
				maxDust = Math.max(maxDust, in);
				minDust = Math.min(minDust, in);
			}
		}
	}
}
