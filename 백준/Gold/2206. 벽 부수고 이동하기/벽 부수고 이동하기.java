import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int x; 
		int y; 
		int isWall; 
		int move;
		
		Node(int x, int y, int isWall, int move) {
			this.x = x; 
			this.y = y; 
			this.isWall = isWall; 
			this.move = move;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, ANSWER;
	static int[][] map;
	static boolean[][][] iv; 
	
	static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0} };
	
	public static void main(String[] args) throws IOException {
		init();
		ANSWER = bfs(new Node(0, 0, 0, 1));
		System.out.println(ANSWER);
	}
	
	private static int bfs(Node startNode) {
		ArrayDeque<Node> Q = new ArrayDeque<>();
		Q.offer(startNode);
		
		while (!Q.isEmpty()) {
			Node cur = Q.poll();

			if(isExit(cur.x, cur.y)) return cur.move; // if current position is exit, return.
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1], nextWall = cur.isWall, move = cur.move;

				
				if (!isValidMove(nx, ny)) continue; // if not valid move, skip.

				if (isValidMoveWithoutWall(nx, ny, nextWall)) { // next Movement without breaking the wall,
					iv[nx][ny][nextWall] = true;
					Q.offer(new Node(nx, ny, nextWall, move+1));
				}
				else if (isValidMoveWithWall(nx, ny, nextWall)) { // next Movement with breaking the wall.
					iv[nx][ny][1] = true;
					Q.offer(new Node(nx, ny, 1, move+1));
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isExit(int nx, int ny) {
		return nx==N-1 && ny==M-1;
	}
	
	private static boolean isValidMove(int nx, int ny) { 
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}
	
	private static boolean isValidMoveWithoutWall(int nx, int ny, int nextWall) {
		return map[nx][ny]==0 && !iv[nx][ny][nextWall];
	}
	
	private static boolean isValidMoveWithWall(int nx, int ny, int nextWall) {
		return map[nx][ny]==1 && nextWall==0 && !iv[nx][ny][1];
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		iv = new boolean[N][M][2];
		iv[0][0][0] = true;
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			String row = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = row.charAt(m)-'0';
			}
		}
	}
}