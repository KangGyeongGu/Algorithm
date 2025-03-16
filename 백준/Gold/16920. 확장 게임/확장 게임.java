import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int x; 
		int y;
		char cN;
		int turn;
		int leftExpand;
		
		Node (int x, int y, char cN, int turn, int leftExpand) {
			this.x = x;
			this.y = y;
			this.cN = cN;
			this.turn = turn;
			this.leftExpand = leftExpand;
		}
	}
	
	static int N, M, P;
	static int[] sp, ANS;
	static char[][] map;
	static boolean[][] iv;
	
	static PriorityQueue<Node> Q = new PriorityQueue<>((o1, o2) -> {
	    if (o1.turn != o2.turn) return Integer.compare(o1.turn, o2.turn); 
	    if (o1.cN != o2.cN) return Integer.compare(o1.cN, o2.cN);
	    return Integer.compare(o2.leftExpand, o1.leftExpand); 
	});

	
	static final int[][] DIR = { {0,1}, {0,-1}, {1,0}, {-1,0} };
	
	public static void main(String[] args) throws IOException {
		init();
		bfs();
		countCastle();
	}
	
	private static void countCastle() {
		ANS = new int[P+1];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == '.' || map[r][c] == '#') continue;
				ANS[map[r][c]-'0']++;
			}
		}
		
		for (int i = 1; i <= P; i++) {
			System.out.print(ANS[i] + " ");
		}
	}
	
	private static void bfs() {
		
		while (!Q.isEmpty()) {

			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
					
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
					
				if (!isValid(nx, ny) || iv[nx][ny]) continue;
					
				iv[nx][ny] = true;
				map[nx][ny] = cur.cN;
				
				if (cur.leftExpand > 1) Q.offer(new Node(nx, ny, cur.cN, cur.turn, cur.leftExpand-1));
				else Q.offer(new Node(nx, ny, cur.cN, cur.turn+1, sp[cur.cN-'0']));
			}
		}
	}
	
	private static boolean isValid(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == '.'; 
	}
	
	private static void init() throws IOException {
		N = read();
		M = read();
		P = read();
		
		sp = new int[P+1];
		for (int i = 1; i <= P; i++) {
			sp[i] = read();
		}
		
		iv = new boolean[N][M];
		map = new char[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = (char) System.in.read(); 
				if (map[r][c] != '.' && map[r][c] != '#') {
					iv[r][c] = true;
					Q.offer(new Node(r, c, map[r][c], 0, sp[map[r][c]-'0']));
				}
			}
			System.in.read(); 
		}
	}
	
	static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
}