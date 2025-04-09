import java.io.*;
import java.util.*;

public class Main {
	
	static class Coords {
		int x, y, time;
		boolean isDrown;
		
		public Coords(int x, int y, int time, boolean isDrown) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.isDrown = isDrown;
		}
	}
	
	static final char CAVE = 'D', HEDGEHOG = 'S', EMPTY = '.', DROWN = '*', ROCK = 'X';
	static final int[][] DIR = { {-1,0}, {1,0}, {0,1}, {0,-1} };
	
	static int R, C, stx, sty;
	static char[][] forest;
	static boolean[][] iv;
	static Queue<Coords> Q = new ArrayDeque<>();
	
	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		iv = new boolean[R][C];
		forest = new char[R][C];
		for (int i = 0; i < R; i++) {
			String in = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = in.charAt(j);
				
				forest[i][j] = ch;
				
				if (ch == HEDGEHOG) {
					stx = i; sty = j;
					iv[i][j] = true;
				}
				else if (ch == DROWN) { // 1. add all water's coordinates in the Queue, first.
					iv[i][j] = true;
					Q.offer(new Coords(i, j, 0, true));
				}
			}
		}
		// 2. hog must be added to the Queue at last.
		Q.offer(new Coords(stx, sty, 0, false));
	}
	
	private static String bfs() {
		while (!Q.isEmpty()) { // Queue Outer Loop
			int qSize = Q.size();

			while (qSize-->0) { // Queue Inner Loop : for Each Minute. (each Qsize means 1 minute.)
				Coords cur = Q.poll();
				
				for (int[] dir : DIR) {
					int nx = cur.x + dir[0], ny = cur.y + dir[1];
					
					if (R <= nx || nx < 0 || C <= ny || ny < 0 || iv[nx][ny]) continue;
					
					if (forest[nx][ny] == DROWN || forest[nx][ny] == ROCK) continue;
					
					// 1. processing Water first,
					if (cur.isDrown) {
						if (forest[nx][ny] == CAVE) continue;
						forest[nx][ny] = DROWN;
						iv[nx][ny] = true;
						Q.offer(new Coords(nx, ny, cur.time+1, true));
					}
					
					// 2. after processing all water movement, update hog's movement.
					else {
						if (forest[nx][ny] == CAVE) return Integer.toString(cur.time+1);
						iv[nx][ny] = true;
						Q.offer(new Coords(nx, ny, cur.time+1, false));
					}
				}
			}
		}
		
		// if bfs()'s while loop couldn't return right answer, this method must return wrong answer,
		return "KAKTUS";
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(bfs());
	}
}