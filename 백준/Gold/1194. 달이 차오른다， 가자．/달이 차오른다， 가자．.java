import java.util.*;
import java.io.*;

public class Main {

	static class Coords { 
		int x, y, key, moveCount;

		public Coords(int x, int y, int key, int moveCount) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.moveCount = moveCount;
		} 
	}
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	static final char EMPTY = '.', WALL = '#', CURR = '0', EXIT = '1';
	
	static int N, M, stx, etx;
	static char[][] maze;
	static boolean[][][] iv;
	
	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iv = new boolean[N][M][64];
		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = in.charAt(j);
				if (maze[i][j] == CURR) { stx = i; etx = j; }
			}
		}
	}
	
	private static boolean isArrayOutOfIndex(int nx, int ny) {
		return N <= nx || nx < 0 || M <= ny || ny < 0;
	}
	
	private static boolean isKey(int nx, int ny) {
		return 'a' <= maze[nx][ny] && maze[nx][ny] <= 'f';
	}
	
	private static boolean isDoor(int nx, int ny) {
		return 'A' <= maze[nx][ny] && maze[nx][ny] <= 'F';
	}
	
	private static int bfs() {
		
		Queue<Coords> Q = new ArrayDeque<>();
		Q.offer(new Coords(stx, etx, 0, 0));
		iv[stx][etx][0] = true;
		
		while (!Q.isEmpty()) {
			Coords cur = Q.poll();
			
			// 1. check if find the exit,
			if (maze[cur.x][cur.y] == EXIT) return cur.moveCount;
			
			// 2. check next move,
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1], key = cur.key, moveCount = cur.moveCount;
				
				// 2-1. if next Coordinate (1) is out of array or (2) is already visited or (3) is wall
				if (isArrayOutOfIndex(nx, ny) || iv[nx][ny][key] || maze[nx][ny] == WALL) continue;
				
				// 2-2. if next Coordinate is key, then get the key,
				if (isKey(nx, ny)) key = cur.key | (1 << (maze[nx][ny] - 'a'));
				
				// 2-3. if next Coordinate is Door, check if user has key.
				if (isDoor(nx, ny)) if ((key & ( 1 << (maze[nx][ny]-'A'))) == 0) continue;

				// if next Coordinate is available moving point, move.
				iv[nx][ny][key] = true;
				Q.offer(new Coords(nx, ny, key, moveCount+1));
			}
		}
		
		// 3. if there's no answer, return -1.
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(bfs());
	}
}