import java.util.*;
import java.io.*;

public class Main {

	static class Node {
		int x; 
		int y;
		int timer;
		boolean isFire;
		
		Node(int x, int y, int timer, boolean isFire) {
			this.x = x;
			this.y = y;
			this.timer = timer;
			this.isFire = isFire;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int R, C, ANSWER;
	static char[][] maze;
	static boolean[][] iv;
	static Queue<Node> Q = new ArrayDeque<>();
	
	static final char SAFE = '.', FIRE = 'F';
	static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0} };
	
	public static void main(String[] args) throws IOException {
		init();
		ANSWER = bfs(); 
		if (ANSWER == -1) System.out.println("IMPOSSIBLE");
		else System.out.println(ANSWER);
		
	}
	
	private static int bfs() {
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0]; 
				int ny = cur.y + dir[1];
				
				if (cur.isFire) {
					if(!isValid(nx, ny)) continue;

					maze[nx][ny] = FIRE;
					iv[nx][ny] = true;
					Q.offer(new Node(nx, ny, cur.timer+1, true));
				}
				else {
					if (isEXIT(nx, ny)) return cur.timer + 1;
					
					if (isValid(nx, ny)) {
						iv[nx][ny] = true;
						Q.offer(new Node(nx, ny, cur.timer+1, false));
					}
				}
			}
			
		}
		return -1;
	}
	
	private static boolean isEXIT(int nx, int ny) {
		return R <= nx || nx < 0 || C <= ny || ny < 0;
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0<=nx && nx<R && 0<=ny && ny<C && !iv[nx][ny] && maze[nx][ny] == SAFE;
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] J = new int[] {0,0};
		
		iv = new boolean[R][C];
		maze = new char[R][C];
		for (int r = 0; r < R; r++) {
			String in = br.readLine();
			for (int c = 0; c < C; c++) {
				maze[r][c] = in.charAt(c);
				if (maze[r][c]=='J') {
					J[0] = r; J[1] = c;
				}
				if (maze[r][c]=='F') Q.offer(new Node(r, c, 0, true));
			}
		}
		
		iv[J[0]][J[1]] = true;
		Q.offer(new Node(J[0], J[1], 0, false));
	}
}
