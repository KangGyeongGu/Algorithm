import java.io.*;
import java.util.*;

public class Main {

	static class Coords {
		int x, y;
		Coords (int x, int y) {
			this.x = x; this.y = y;
		}
	}
	
	static int N, M, ANS;
	static Coords startPos;
	static char[][] campus;
	static boolean[][] visited;
	
	static Queue<Coords> Q;
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		campus = new char[N][M];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < M; j++) {
				campus[i][j] = in.charAt(j);
				if (campus[i][j] == 'I') startPos = new Coords(i, j);
			}
		}
	}
	
	private static void bfs() {
		Q = new ArrayDeque<>();
		Q.offer(startPos);
		
		visited = new boolean[N][M];
		visited[startPos.x][startPos.y] = true;
		
		while (!Q.isEmpty()) {
			Coords cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || campus[nx][ny] == 'X' || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				if (campus[nx][ny] == 'P') ANS++;
				
				Q.offer(new Coords(nx, ny));
			}
		}
		
		System.out.println(ANS == 0 ? "TT" : ANS);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		bfs();
	}
}