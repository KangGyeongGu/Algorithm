import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
		int x, y;
		Node (int x, int y) { this.x = x; this.y = y; }
	}
	
	static int N, M;
	static Node target;
	static int[][] map;
	static boolean[][] iv;
	static Queue<Node> Q = new ArrayDeque<>();
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        iv = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int node = Integer.parseInt(st.nextToken());
				map[i][j] = node;
				
				if (node == 2) target = new Node(i, j);
			}
		}
        
        bfs();
    }
	
	private static void bfs() {
		StringBuilder sb = new StringBuilder();
		int[][] temp = new int[N][M];
		Q.offer(target);
		iv[target.x][target.y] = true;
		temp[target.x][target.y] = 0;
		
		int depth = 1;
		while (!Q.isEmpty()) {
			
			int size = Q.size();
			
			while (size-->0) {
				
				Node cur = Q.poll();
				
				for (int[] dir : DIR) {
					int nx = cur.x + dir[0];
					int ny = cur.y + dir[1];
					
					if (N <= nx || nx < 0 || M <= ny || ny < 0 || map[nx][ny] == 0) continue;
					
					if (iv[nx][ny]) continue;
					
					if (map[nx][ny] == 1) {
						iv[nx][ny] = true;
						temp[nx][ny] = depth;
						Q.offer(new Node(nx, ny));
					}
				}
			}
			depth++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if (temp[i][j] == 0 && map[i][j] == 1) sb.append(-1 + " ");
				else sb.append(temp[i][j] + " ");
			} sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
}
