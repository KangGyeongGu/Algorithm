import java.io.*;
import java.util.*;

public class Main {
	
	static int N, ANS;
	static int[][] map;
	static int[][] dist;
	static boolean[][] isIsland;
	static Queue<Integer> Q; 
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		findIslandBfs();
		findBridgeBfs();
		System.out.println(ANS);
	}
	
	private static void findBridgeBfs() {
		distInit();
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur/N + dir[0], ny = cur%N + dir[1];
				
				if (!isValid(nx, ny)) continue;
				
				if (map[nx][ny] == map[cur/N][cur%N]) continue;
				
				if (map[nx][ny] != 0) {
					ANS = Math.min(ANS, dist[nx][ny] + dist[cur/N][cur%N]);
				}
				else {
					map[nx][ny] = map[cur/N][cur%N];
					dist[nx][ny] = dist[cur/N][cur%N] + 1;
					Q.offer(nx*N+ny);
				}
			}
		}
	}
	
	private static void distInit() {
		Q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					dist[i][j] = 0;
					Q.offer(i*N+j);
					continue;
				}
				dist[i][j] = -1; 
 			}
		}
	}
	
	private static void findIslandBfs() {
		int islandIdx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !isIsland[i][j]) {
					isIslandBfs(i, j, islandIdx);
					islandIdx++;
				}
			}

		}
	}
	
	private static void isIslandBfs(int x, int y, int islandIdx) {
		Q = new ArrayDeque<>();
		Q.offer(x*N + y);
		isIsland[x][y] = true;
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			map[cur/N][cur%N] = islandIdx;
			
			for (int[] dir : DIR) {
				int nx = cur/N + dir[0], ny = cur%N + dir[1];
				
				if (!isValid(nx, ny) || isIsland[nx][ny] || map[nx][ny] == 0) continue;
				
				isIsland[nx][ny] = true;
				
				Q.offer(nx*N+ny);
			}
			
		}
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N; 
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dist = new int[N][N];
		isIsland = new boolean[N][N];
		ANS = Integer.MAX_VALUE;
	}
}
