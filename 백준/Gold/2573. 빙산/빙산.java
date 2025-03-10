import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[][] map, tmp;
	
	static Queue<int[]> Q = new ArrayDeque<>();
	static Queue<int[]> Q2 = new ArrayDeque<>();
	static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0} };
	
	public static void main(String[] args) throws IOException{
		init();
		System.out.println(meltdown());
	}
	
	private static int meltdown() {
		int year = 0;
		
		while (!Q.isEmpty()) {
			
			int qSize = Q.size();
			
			while (qSize-- > 0) {
				int[] cur = Q.poll();
				int cx = cur[0]; 
				int cy = cur[1];

				for (int[] dir : DIR) {
					int nx = cx + dir[0];
					int ny = cy + dir[1];

					if (!isValid(nx, ny)) continue;
					
					if (map[nx][ny] == 0 && tmp[cx][cy] > 0) tmp[cx][cy]--;
				}
				
				if (tmp[cx][cy] == 0) continue;
				
				Q.offer(new int[] {cx, cy});
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = tmp[i][j];
				}
			}
			year++;
			
			qSize = Q.size();
			
			int total = countIcebergs();
			
			if (total > 1) return year;
			
		}
		
		return 0;
	}
	
	private static int countIcebergs() {
	    boolean[][] visited = new boolean[N][M];
	    int count = 0;

	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if (map[i][j] > 0 && !visited[i][j]) {
	                bfs(i, j, visited);
	                count++;
	            }
	        }
	    }
	    return count;
	}

	private static void bfs(int x, int y, boolean[][] visited) {
	    Queue<int[]> queue = new ArrayDeque<>();
	    queue.offer(new int[]{x, y});
	    visited[x][y] = true;

	    while (!queue.isEmpty()) {
	        int[] cur = queue.poll();
	        int cx = cur[0], cy = cur[1];

	        for (int[] dir : DIR) {
	            int nx = cx + dir[0];
	            int ny = cy + dir[1];

	            if (isValid(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
	                visited[nx][ny] = true;
	                queue.offer(new int[]{nx, ny});
	            }
	        }
	    }
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0<=nx && nx<N && 0<=ny && ny<M;
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tmp = new int[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int in = Integer.parseInt(st.nextToken());
				map[i][j] = in;
				tmp[i][j] = in;
				
				if (in != 0) Q.offer(new int[] {i, j});
			}
		}
	}
}
