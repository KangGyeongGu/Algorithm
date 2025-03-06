import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int h;
		int x;
		int y;
		int time;
		
		public Node(int h, int n, int m, int time) {
			this.h = h;
			this.x = n;
			this.y = m;
			this.time = time; 
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, H, unRipedCount, minDay;
	static int[][][] box;
	
	static Queue<Node> Q = new ArrayDeque<>();
	
	static final int RIPED = 1, UNRIPED = 0, EMPTY = -1;
	static final int[][] DIR = { {0,0,1}, {0,0,-1}, {0,1,0}, {0,-1,0}, {1,0,0}, {-1,0,0} }; // {H, N, M}
	
	public static void main(String[] args) throws IOException {
		init();
		
		if (unRipedCount==0) { // if there's no UNRIPED tomato after the initialization,
			System.out.println(0);
			return;
		}
		
		bfs(); // start simulation,
		
		if (unRipedCount > 0) System.out.println(-1);
		else System.out.println(minDay);
	}
	
	private static void bfs() {
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			minDay = cur.time; // update current time,
			
			for (int[] dir : DIR) {
				int nh = cur.h + dir[0], nx = cur.x + dir[1], ny = cur.y + dir[2];

				if (!isValidSpread(nh, nx, ny)) continue;

				unRipedCount--; // discount current unriped tomato,
				box[nh][nx][ny] = RIPED; // changed box status
				Q.offer(new Node(nh, nx, ny, cur.time+1));
			}
		}
	}
	
	private static boolean isValidSpread(int nh, int nx, int ny) { // find UNRIPED tomato in valid box range,
		return 0<=nh && nh<H && 0<=nx && nx<N && 0<=ny && ny<M && box[nh][nx][ny]==UNRIPED; 
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					int elem = Integer.parseInt(st.nextToken());
					box[i][j][k] = elem;
					
					if (elem == RIPED) {
						Q.offer(new Node(i, j, k, 0));
					}
					else if (elem == UNRIPED) {
						unRipedCount++;
					}
				}
			}
		}
	}
}