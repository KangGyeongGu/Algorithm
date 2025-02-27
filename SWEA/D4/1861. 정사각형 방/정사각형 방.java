import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, rC;
	static int[][] room, check;
	static boolean[][] iv;
	static ArrayDeque<int[]> q;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	private static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			search();
		}
		System.out.println(sb);
	}
	
	private static void search() {
		int roomNumber = Integer.MAX_VALUE;
		int maxCount = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				q.clear(); iv = new boolean[N][N]; iv[i][j] = true; rC = 1;
				bfs(new int[] {i, j});
				check[i][j] = rC;
				
				if (check[i][j] > maxCount) {
					maxCount = check[i][j];
					roomNumber = room[i][j];
				}
				else if (check[i][j] == maxCount) {
					roomNumber = Math.min(roomNumber, room[i][j]);
				}
			}
		}
		sb.append(roomNumber).append(" ").append(maxCount).append('\n');
	}
	
	private static void bfs(int[] start) {
		q.offer(start);
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0]; int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + DIR[i][0]; 
				int ny = y + DIR[i][1];
				
				if (!isValidRange(nx, ny) || !isValidRoom(x, y, nx, ny)) continue;
				
				iv[nx][ny] = true;
				q.offer(new int[] {nx, ny});
				rC++;
			}
		}
	}
	
	private static boolean isValidRoom(int x, int y, int nx, int ny) {
		return room[nx][ny] - room[x][y] == 1 ? true : false; 
	}
	
	private static boolean isValidRange(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N; 
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());
		room = new int[N][N]; 
		check = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				room[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		q = new ArrayDeque<>();
	}
}
