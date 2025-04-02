import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DIR = { {-1,0}, {0,-1}, {1,0}, {0,1} }; // up left down right
	static final boolean[][] PIPE = { // All pipes available directions,
			{false, false, false, false},
			{true, true, true, true}, 	 // PIPE 1
			{true, false, true, false}, // PIPE 2
			{false, true, false, true},  // PIPE 3
			{true, false, false, true},	 // PIPE 4
			{false, false, true, true},	 // PIPE 5
			{false, true, true, false},  // PIPE 6
			{true, true, false, false}	 // PIPE 7
	};
	
	static class Coord {
		int x, y, time;
		Coord (int x, int y, int time) { this.x = x; this.y = y; this.time = time; }
	}
	
	static int T, N, M, R, C, L, map[][];
	static boolean[][] iv;
	static Queue<Coord> Q;
	
	private static void init(int tC) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		iv = new boolean[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static boolean isOutOfRange(int nx, int ny) {
		return N <= nx || nx < 0 || M <= ny || ny < 0;
	}
	
	private static int bfs() {
		Q = new ArrayDeque<>();
		Q.offer(new Coord(R, C, 1));
		iv = new boolean[N][M];
		iv[R][C] = true;
		
		int ANS = 1;
		
		while (!Q.isEmpty()) {
			Coord cur = Q.poll();
			int PIPE_DIRECTION = map[cur.x][cur.y];	
			
			if (cur.time == L) continue;
			
			for (int i = 0; i < 4; i++) {
				if (!PIPE[PIPE_DIRECTION][i]) continue;
				
				int nx = cur.x + DIR[i][0];
				int ny = cur.y + DIR[i][1];
				if (isOutOfRange(nx, ny) || iv[nx][ny]) continue;
 				
				int NEXT_PIPE_DIRECTION = map[nx][ny];
				int nextI = (i+2) % 4;
				if (!PIPE[NEXT_PIPE_DIRECTION][nextI]) continue;
					
				iv[nx][ny] = true;
				Q.offer(new Coord(nx, ny, cur.time+1));
				ANS++;
			}
		}
		
		return ANS;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);
	}
}
