import java.io.*;
import java.util.*;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int SIZE = 4001;
	static final int OFFSET = 2000;
	static final int[][] DIR = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };

	static class Atom {
		int x, y, dir, eV;
		boolean alive;

		Atom(int x, int y, int dir, int eV) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.eV = eV;
			this.alive = true;
		}

		void move() {
			x += DIR[dir][0];
			y += DIR[dir][1];
		}

		boolean outOfBounds() {
			return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
		}

		int key() {
			return x * SIZE + y;
		}
	}

	static int N, totalEnergy;
	static Queue<Atom> queue = new ArrayDeque<>();
	static Map<Integer, List<Atom>> map = new HashMap<>();

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		queue.clear();
		map.clear();
		totalEnergy = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = (Integer.parseInt(st.nextToken()) * 2) + OFFSET;
			int y = (Integer.parseInt(st.nextToken()) * 2) + OFFSET;
			int dir = Integer.parseInt(st.nextToken());
			int eV = Integer.parseInt(st.nextToken());
			queue.offer(new Atom(x, y, dir, eV));
		}
	}

	private static int simulate() {
		while (!queue.isEmpty()) {
			map.clear();
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Atom atom = queue.poll();

				atom.move();
				if (atom.outOfBounds()) {
					atom.alive = false;
					continue;
				}
				map.computeIfAbsent(atom.key(), k -> new ArrayList<>()).add(atom);
			}

			for (List<Atom> list : map.values()) {
				if (list.size() >= 2) {
					for (Atom a : list) {
						if (a.alive) {
							a.alive = false;
							totalEnergy += a.eV;
						}
					}
				} else {
					Atom atom = list.get(0);
					if (atom.alive) queue.offer(atom);
				}
			}
		}
		return totalEnergy;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			bw.write("#" + tc + " " + simulate() + "\n");
		}
		bw.flush();
		bw.close();
	}
}
