import java.io.*;
import java.util.*;

/**
 * 
 * 1. 구슬 쏘기 backtrack
 *  > 모든 열에 대한 구슬 쏘기 경우의 수 백트래킹
 * 
 * 2. 벽돌 제거 bfs
 * 	> 맞은 벽돌 기준 연쇄 제거
 * 
 * 3. 벽돌 내리기
 * 	> 제거 후 중력 적용해서 아래로 내림
 * 
 * 4. 남은 벽돌 세기
 *  > 폭발 후 남은 벽돌 개수 카운트
 * 
 */
public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static class Coords {
		int x, y, range;
		Coords(int x, int y, int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}

	static final int[][] DIR = { {-1,0}, {1,0}, {0,-1}, {0,1} };

	static int T, N, W, H, map[][], ANS;
	static Queue<Coords> Q = new ArrayDeque<>();

	private static int[][] mapCopy(int[][] origin) {
		int[][] tempMap = new int[H][W];
		for (int x = 0; x < H; x++) for (int y = 0; y < W; y++) tempMap[x][y] = origin[x][y];
		return tempMap;
	}

	private static boolean isArrayOutOfIndex(int x, int y) {
		return y < 0 || y >= W || x < 0 || x >= H;
	}

	/**
	 * (1) 게임 초기화
	 */
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for (int x = 0; x < H; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < W; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		ANS = Integer.MAX_VALUE;
	}

	/**
	 * (2) 구슬 떨어뜨리기
	 * 
	 * @param curMap 현재 상태 맵
	 * @param y	 구슬을 떨어뜨릴 열
	 * @return 	 맞춘 벽돌이 있는지 여부 (t/f)
	 */
	private static boolean shooting(int[][] curMap, int y) {
		for (int x = 0; x < H; x++) {
			if (curMap[x][y] > 0) {
				explosionChainingBFS(curMap, x, y);
				return true;
			}
		}
		return false;
	}

	/**
	 * (3) 인접 벽돌 연쇄 폭발 처리 (BFS)
	 * 
	 * @param curMap 현재 상태 맵
	 * @param x	 전파가 시작될 벽돌 x 좌표
	 * @param y	 전파가 시작될 벽돌 y 좌표
	 */
	private static void explosionChainingBFS(int[][] curMap, int x, int y) {
		Q.clear();
		Q.offer(new Coords(x, y, curMap[x][y]));
		curMap[x][y] = 0;

		while (!Q.isEmpty()) {
			Coords cur = Q.poll();

			for (int[] dir : DIR) {
				for (int r = 1; r < cur.range; r++) {
					int nx = cur.x + r * dir[0], ny = cur.y + r * dir[1];

					if (isArrayOutOfIndex(nx, ny) || curMap[nx][ny] == 0) continue;

					Q.offer(new Coords(nx, ny, curMap[nx][ny]));
					curMap[nx][ny] = 0;
				}
			}
		}
	}

	/**
	 * (4) 연쇄 폭발 이후 벽돌 중력 작용
	 * 
	 * @param curMap 현재 상태 맵
	 */
	private static void bricksDown(int[][] curMap) {
		for (int y = 0; y < W; y++) {
			int writeX = H - 1;
			for (int x = H - 1; x >= 0; x--) {
				if (curMap[x][y] != 0) {
					if (writeX != x) {
						curMap[writeX][y] = curMap[x][y];
						curMap[x][y] = 0;
					}
					writeX--;
				}
			}
		}
	}

	/**
	 * (5) 벽돌 중력 작용 이후 남은 벽돌 개수 카운트
	 * 
	 * @param curMap 현재 상태 맵
	 * @return 	 현재 상태 맵에 남아있는 벽돌 개수
	 */
	private static int countRemainingBricks(int[][] curMap) {
		int remainings = 0;
		for (int x = 0; x < H; x++) for (int y = 0; y < W; y++) if (curMap[x][y] != 0) remainings++;
		return remainings;
	}

	/**
	 * (6) 구슬을 떨어뜨릴 수 있는 모든 열의 경우의 수에 대한 시뮬레이션 수행
	 * 
	 * @param depth  현재 구슬 사용 횟수
	 * @param curMap 현재 상태 맵
	 */
	private static void backtrack(int depth, int[][] curMap) {
		if (depth == N) {
			ANS = Math.min(ANS, countRemainingBricks(curMap));
			return;
		}

		for (int y = 0; y < W; y++) {
			int[][] tempMap = mapCopy(curMap);

			if (shooting(tempMap, y)) {
				bricksDown(tempMap);
				backtrack(depth + 1, tempMap);
			} else {
				backtrack(depth + 1, tempMap);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			
			backtrack(0, map);
			bw.write("#" + tc + " " + ANS + "\n");
		}
		bw.flush(); bw.close();
	}
}