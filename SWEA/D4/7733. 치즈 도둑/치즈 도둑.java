import java.io.*;
import java.util.*;

public class Solution {

	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, UPPERBOUND, lump, MAXLUMP;
	static int[][] arr;
	
	static boolean[][] iv;
	static Queue<Node> Q = new ArrayDeque<>();
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			simulate();
		}
		
		System.out.println(sb);
	}
	
	private static void simulate() {
		for (int i = 0; i <= UPPERBOUND; i++) {
			run(i);
			MAXLUMP = Math.max(MAXLUMP, lump);
			Q.clear();
		}
		
		sb.append(MAXLUMP).append('\n');
	}
	
	private static void run(int upperbound) {
		lump = 0;
		iv = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!iv[i][j] && arr[i][j] > upperbound) {
					iv[i][j] = true;
					Q.offer(new Node(i, j));
					bfs(upperbound);
					lump++;
				}
			}
		}
	}
	
	private static void bfs(int upperbound) {
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (!isValidMove(nx, ny, upperbound)) continue;
				
				iv[nx][ny] = true;
				Q.offer(new Node(nx, ny));
			}
		}
	}
	
	private static boolean isValidMove(int nx, int ny, int upperbound) {
		return 0<=nx && nx<N && 0<=ny && ny<N && !iv[nx][ny] && arr[nx][ny]>upperbound;
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		
		N = Integer.parseInt(br.readLine());
		
		UPPERBOUND = Integer.MIN_VALUE;
		MAXLUMP = Integer.MIN_VALUE;
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int p = Integer.parseInt(st.nextToken());
				UPPERBOUND = Math.max(UPPERBOUND, p);
				arr[i][j] = p;
			}
		}
	}
}
