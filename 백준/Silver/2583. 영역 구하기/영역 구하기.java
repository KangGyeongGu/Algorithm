import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int M, N, K;
	static int[][] map;
	static int[][] papers;
	static List<Integer> list = new ArrayList<>();
	
	static boolean[][] iv;
	static ArrayDeque<int[]> Q = new ArrayDeque<>();
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	private static void run() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 1 && !iv[i][j]) {
					Q.clear();
					iv[i][j] = true; 
					bfs(i, j);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for (int li : list) {
			System.out.print(li + " ");
		}
	}
	
	private static void bfs(int x, int y) {
		Q.offer(new int[] {x, y});
		
		int cnt = 1;
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
				
				if (!isValidMove(nx, ny)) continue;
				
				cnt++;
				iv[nx][ny] = true;
				Q.offer(new int[] {nx, ny});
			}
		}
		
		list.add(cnt);
	}
	
	private static boolean isValidMove(int nx, int ny) {
		return 0 <= nx && nx < M && 0 <= ny && ny < N && !iv[nx][ny] && map[nx][ny] == 0;
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		papers = new int[K][4];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				papers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		iv = new boolean[M][N];
		map = new int[M][N];
		for (int[] paper : papers) {
			int sc = paper[0], sr = paper[1], ec = paper[2], er = paper[3];
			
			for (int i = sr; i < er; i++) {
				for (int j = sc; j < ec; j++) {
					map[i][j] = 1;
				}
			}
		}
	}
}
