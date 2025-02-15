import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int M, N, timer;
	static int[][] container;
	static Queue<int[]> q = new ArrayDeque<>();
	
	static int[][] dir = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		initializeQueue();
		bfs();
		System.out.println(isMaturedContainer() ? timer : -1);
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		container = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				container[r][c] = Integer.parseInt(st.nextToken());  
			}
		}
	}
	
	private static void initializeQueue() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (container[r][c] == 1) {
                    q.offer(new int[] {r, c, 0});
                }
            }
        }
    }
	
	private static void bfs() {
		while (!q.isEmpty()) {
			int[] cPos = q.poll();
			int x = cPos[0], y = cPos[1], t = cPos[2];
			timer = t;
			
			for (int[] d : dir) {
				int nx = x + d[0], ny = y + d[1];
				
				if(isValidPos(nx, ny)) {
					q.offer(new int[] {nx, ny, t+1});
					container[nx][ny] = 1; 
				}
			}
		}
	}
	
	private static boolean isValidPos(int nx, int ny) {
	    return nx >= 0 && nx < N && ny >= 0 && ny < M && container[nx][ny] == 0;
	}
	
	private static boolean isMaturedContainer() {
		for (int[] row : container) {
			for (int tomato : row) {
				if (tomato == 0) return false;
			}
		}
		return true;
	}
}