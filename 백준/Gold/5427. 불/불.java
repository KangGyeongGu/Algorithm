import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
		int x; 
		int y;
		int time;
		boolean isFire;
		
		Node(int x, int y, int time, boolean isFire) {
			this.x = x; 
			this.y = y;
			this.time = time;
			this.isFire = isFire;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, W, H;
	static Node curPos;
	static boolean[][] iv;
	static char[][] BUILDING;
	static ArrayDeque<Node> Q = new ArrayDeque<>();
	
	static final char EMPTY = '.', WALL = '#', START = '@', FIRE = '*';
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		while (T-->0) {
			init();
			run();
		}
		System.out.println(sb);
	}
	
	private static void run() {
		int result = bfs();
		
		if (result == -1) sb.append("IMPOSSIBLE").append('\n');
		else sb.append(result).append('\n');
	}
	
	private static int bfs() {
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (cur.isFire) {
					if (!isValidSpread(nx, ny)) continue;
					BUILDING[nx][ny] = FIRE;
					Q.offer(new Node(nx, ny, cur.time+1, true));
				}
				else {
					if (isExit(nx, ny)) return cur.time + 1;
					
					if (isValidMove(nx, ny)) {
						iv[nx][ny] = true;
						Q.offer(new Node(nx, ny, cur.time+1, false));
					}
				}
			}
		}
		return -1;
	}
	
	private static boolean isValidSpread(int nx, int ny) {
		return 0 <= nx && nx < H && 0 <= ny && ny < W && BUILDING[nx][ny] == EMPTY;
	}
	
	private static boolean isExit(int nx, int ny) {
		return nx < 0 || nx >= H || ny < 0 || ny >= W;
	}
	
	private static boolean isValidMove(int nx, int ny) {
		return 0 <= nx && nx < H && 0 <= ny && ny < W && !iv[nx][ny] && BUILDING[nx][ny] != WALL && BUILDING[nx][ny] != FIRE;  
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		Q.clear();
		iv = new boolean[H][W];
		BUILDING = new char[H][W];
		for (int h = 0; h < H; h++) {
			char[] in = br.readLine().toCharArray();
			for (int w = 0; w < W; w++) {
				BUILDING[h][w] = in[w];
				if (BUILDING[h][w]==START) curPos = new Node(h, w, 0, false);
				else if (BUILDING[h][w]==FIRE) Q.offer(new Node(h, w, 0, true));
			}
		}
		
		Q.offer(curPos);
	}
}
