import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = 0, sign = 1; while ((c = System.in.read()) <= 32);
		if (c == '-') { sign = -1; c = System.in.read(); } n = c & 15;
		while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read(); return n * sign;
	}

	static final int[][] DIR = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // up down left right,
	static final int[][] blockDir = { {}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2} };
	
	static class Coords { int x, y; Coords(int x, int y) { this.x = x; this.y = y; } }

	static int T, N, map[][];
	static Map<Integer, List<Coords>> wormholes; // { "holeId" : "coordinates" }

	private static void init() throws Exception {
		N = read();
		map = new int[N][N];
		wormholes = new HashMap<>();

		for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) {
			map[i][j] = read();
			if (map[i][j] >= 6 && map[i][j] <= 10) wormholes.computeIfAbsent(map[i][j], k -> new ArrayList<>()).add(new Coords(i, j));
		}
	}

	private static int changeDir(int block, int dir) { return blockDir[block][dir]; }
	private static boolean isArrayOutOfIndex(int nx, int ny) { return nx < 0 || nx >= N || ny < 0 || ny >= N; }
	private static boolean isBlock(int nx, int ny) { return map[nx][ny] >= 1 && map[nx][ny] <= 5; }
	private static boolean isWormHole(int nx, int ny) { return map[nx][ny] >= 6 && map[nx][ny] <= 10; }
	private static boolean isGameOver(int nx, int ny, int sx, int sy) { return map[nx][ny] == -1 || (nx == sx && ny == sy); }
	
	private static int simulate(int sx, int sy, int dir) {
		int score = 0, nx = sx, ny = sy;
		
		while (true) {
			nx += DIR[dir][0];
			ny += DIR[dir][1];

			if (isArrayOutOfIndex(nx, ny)) {
				dir = (dir % 2 == 0) ? dir + 1 : dir - 1;
				score++;
				continue;
			}

			if (isGameOver(nx, ny, sx, sy)) break; // if Game is Over,

			if (isBlock(nx, ny)) { // if encountered block, switch direction.
				dir = changeDir(map[nx][ny], dir);
				score++;
			} 
			else if (isWormHole(nx, ny)) { // or encountered wormhole, teleport.
				List<Coords> list = wormholes.get(map[nx][ny]);
				
				if (list.get(0).x == nx && list.get(0).y == ny) {
					nx = list.get(1).x;
					ny = list.get(1).y;
				} 
				else {
					nx = list.get(0).x;
					ny = list.get(0).y;
				}
			}
		}
		return score;
	}
	
	private static int simulation() {
		int maxScore = 0;
		for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) {
			if (map[i][j] != 0) continue;
			for (int d = 0; d < 4; d++) maxScore = Math.max(maxScore, simulate(i, j, d));
		}
		return maxScore;
	}

	public static void main(String[] args) throws Exception {
		T = read();
		
		for (int tc = 1; tc <= T; tc++) {
			init();
			bw.write("#" + tc + " " + simulation() + "\n");
		}
		
		bw.flush(); bw.close();
	}
}