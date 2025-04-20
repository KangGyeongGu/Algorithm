import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Log {
		int x, y, dir, movement;

		public Log(int x, int y, int dir, int movement) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.movement = movement;
		}
	}
	
	static int N, stx, sty, etx, ety, sDir, eDir;
	static char[][] ground;
	static boolean[][][] visited;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	private static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		
		ground = new char[N][N];
		visited = new boolean[N][N][2];
		
		List<int[]> Bs = new ArrayList<>();
		List<int[]> Es = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			ground[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (ground[i][j] == 'B') Bs.add(new int[] {i,j});
				else if (ground[i][j] == 'E') Es.add(new int[] {i,j});
			}
		}
		
		stx = Bs.get(1)[0]; sty = Bs.get(1)[1];
		etx = Es.get(1)[0]; ety = Es.get(1)[1];
		
		sDir = (Bs.get(0)[0] == stx) ? 0 : 1;
		eDir = (Es.get(0)[0] == etx) ? 0 : 1;
	}
	
	private static boolean OOB(int nx, int ny) {
		return N <= nx || nx < 0 || N <= ny || ny < 0;
	}
	
	private static boolean isReached(Log cur) {
		return cur.x == etx && cur.y == ety && cur.dir == eDir;
	}
	
	private static boolean isRotatable(int cx, int cy) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int nx = cx + i;
				int ny = cy + j;
				if (OOB(nx, ny) || ground[nx][ny] == '1') return false;
			}
		}
		return true;
	}
	
	private static boolean movable(int nx, int ny, int nDir) {
		if (OOB(nx, ny)) return false;
		
		if (nDir == 0) { // horizontal
			if (ny-1 < 0 || ny+1 >= N) return false;
			for (int i = -1; i <= 1; i++) if (ground[nx][ny + i] == '1') return false;
		}
		else if (nDir == 1) { // vertical
			if (nx-1 < 0 || nx+1 >= N) return false;
			for (int i = -1; i <= 1; i++) if (ground[nx + i][ny] == '1') return false;
		}
		
		return true;
	}
	
	private static int bfs() {
		Queue<Log> Q = new ArrayDeque<>();
		Q.offer(new Log(stx, sty, sDir, 0));
		visited[stx][sty][sDir] = true;
		
		while (!Q.isEmpty()) {
			Log cur = Q.poll();
			
			if (isReached(cur)) return cur.movement;
			
			for (int[] dir : DIR) { // proceed while maintaining the previous direction,
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (!movable(nx, ny, cur.dir)) continue;
				
				if (visited[nx][ny][cur.dir]) continue;
				
				visited[nx][ny][cur.dir] = true;
				
				Q.offer(new Log(nx, ny, cur.dir, cur.movement+1));
			}
			
			// try rotating,
			int nDir = 1 - cur.dir;
			if (isRotatable(cur.x, cur.y) && !visited[cur.x][cur.y][nDir]) { // if current position is rotatable,
				visited[cur.x][cur.y][nDir] = true;
				Q.offer(new Log(cur.x, cur.y, nDir, cur.movement+1));
			}
		}
		
		return 0; // in condition, if not movable, return 0.
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(bfs());
	}
}
