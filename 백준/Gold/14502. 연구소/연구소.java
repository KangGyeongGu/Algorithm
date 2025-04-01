import java.io.*;
import java.util.*;

public class Main {

	static class Coord {
		int x, y;
		Coord (int x, int y) { this.x = x; this.y = y; }
	}
	
	static int N, M, ANS = Integer.MIN_VALUE;
	static int[][] lab;
	
	static Queue<Coord> Q;
	static List<Coord> emptySpacesList = new ArrayList<>();
	static List<Coord> virusList = new ArrayList<>();
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int elem = Integer.parseInt(st.nextToken());
				lab[i][j] = elem;

				if (elem == 0) emptySpacesList.add(new Coord(i, j));
				if (elem == 2) virusList.add(new Coord(i, j));
			}
		}
	}
	
	private static int[][] arrayCopy() {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) { for (int j = 0; j < M; j++) { temp[i][j] = lab[i][j]; } }
		return temp;
	}
	
	private static int virusDiffusion() {
		int[][] testCase = arrayCopy();
		int safeArea = emptySpacesList.size()-3;
		
		Q = new ArrayDeque<>();
		for (Coord virus : virusList) Q.offer(virus);
		
		while (!Q.isEmpty()) {
			Coord curV = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = curV.x + dir[0];
				int ny = curV.y + dir[1];
				
				if (N <= nx || nx < 0 || M <= ny || ny < 0 || testCase[nx][ny] != 0) continue;

				safeArea--;
				testCase[nx][ny] = 2;
				Q.offer(new Coord(nx, ny));
			}
		}
		
		return safeArea;
	}
	
	private static void combination(int depth, int start) {
		if (depth == 3) {
			ANS = Math.max(ANS, virusDiffusion());
			return;
		}
		
		for (int i = start; i < emptySpacesList.size(); i++) {
			Coord wall = emptySpacesList.get(i);
			
			lab[wall.x][wall.y] = 1;
			combination(depth+1, i+1);
			lab[wall.x][wall.y] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		combination(0, 0);
		System.out.println(ANS);
	}
}