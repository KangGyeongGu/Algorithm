import java.io.*;
import java.util.*;

public class Main {
	
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
	
	static int N, M, timer, prevPieceCount;
	static int[][] arr;
	
	static boolean[][] iv;
	static Queue<Node> Q = new ArrayDeque<>();
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
		init();
		bfs();
		System.out.println(timer);
		System.out.println(prevPieceCount);
	}
	
	private static void bfs() {
		while (true) {
			iv = new boolean[N][M];
			Q.clear();
			
			Q.offer(new Node(0, 0));
			iv[0][0] = true;
			
			int borderCount = 0;
			
			while (!Q.isEmpty()) {
				Node cur = Q.poll();
				
				for (int[] dir : DIR) {
					int nx = cur.x + dir[0], ny = cur.y + dir[1];
					
					if (!isValidMove(nx, ny)) continue;
					
					iv[nx][ny] = true;
					
					if (arr[nx][ny] == 1) { // melt down the border of cheese that contacted outer air,
						borderCount++;
						arr[nx][ny] = 0;
					}
					else {
						Q.offer(new Node(nx, ny)); // go on Outer Air search,
					}
				}
			}
			
			if (borderCount == 0) break; // if there no left cheese (if inner while loop doesn't excuted) return.
			
			prevPieceCount = borderCount; // store current left cheese count as previous count,
			timer++;
		}
	}
	
	private static boolean isValidMove(int nx, int ny) {
		return 0<=nx && nx<N && 0<=ny && ny<M && !iv[nx][ny];
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
	}
}