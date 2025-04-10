import java.io.*;
import java.util.*;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	/**
	 * SIZE	  : 좌표 압축 맵 크기 (0 ~ 4000)
	 * OFFSET : 음수 좌표 처리를 위한 기준  영점 : (2000,2000)
	 * */
	static final int SIZE = 4001;
	static final int OFFSET = 2000;
	static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	/**
	 * - 원자 별 좌표(x, y), 방향(dir), 에너지(eV) 및 생존 여부(alive)
	 * - key()  		: 맵 상에서 원자 별 원좌표를 고유key값으로 치환
	 * - move() 		: 원자 고유 방향으로 한 칸 이동
	 * - isOutOfBound() : 배열 범위 체크
	 * */
	static class Atom {
		int x, y, dir, eV;
		boolean isAlive;

		Atom(int x, int y, int dir, int eV) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.eV = eV;
			this.isAlive = true;
		}

		void move() {
			x += DIR[dir][0];
			y += DIR[dir][1];
		}

		int key() {
			return x * SIZE + y;
		}

		boolean isOutOfBound() {
			return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
		}
	}

	static Queue<Atom> Q = new ArrayDeque<>();
	static Map<Integer, List<Atom>> collisions = new HashMap<>();
	static int N, totalEV;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		Q.clear();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = (Integer.parseInt(st.nextToken()) * 2) + OFFSET;
			int y = (Integer.parseInt(st.nextToken()) * 2) + OFFSET;
			int dir = Integer.parseInt(st.nextToken());
			int eV = Integer.parseInt(st.nextToken());
			Q.offer(new Atom(x, y, dir, eV));
		}
	}

	private static int bfs() {
		totalEV = 0;

		while (!Q.isEmpty()) {
			collisions.clear();

			int qSize = Q.size();

			while (qSize-- > 0) { // 1. 좌표계 상의 모든 원자에 대해 1초 후 상태 갱신
				Atom atom = Q.poll();

				if (!atom.isAlive)
					continue;

				atom.move();

				if (atom.isOutOfBound()) {
					atom.isAlive = false;
					continue;
				}

				collisions.computeIfAbsent(atom.key(), k -> new ArrayList<>()).add(atom);
			}

			for (List<Atom> list : collisions.values()) { // 2. 1초 후 갱신된 상태 확인
				if (list.size() >= 2) { // 2-1. 해당 좌표에서 충돌이 발생한 경우,
					for (Atom atom : list) {
						if (!atom.isAlive)
							continue;
						atom.isAlive = false;
						totalEV += atom.eV;

					}
				} else { // 2-2. 충돌이 발생하지 않은 경우,
					Atom survivor = list.get(0);
					if (survivor.isAlive)
						Q.offer(survivor);
				}
			}
		}
		
		return totalEV;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			bw.write("#" + tc + " " + bfs() + "\n");
		} bw.flush(); bw.close();
	}
}
