import java.io.*;
import java.util.*;

public class Main {
		
	static class Node {
		int n;
		int r;
		int c;
		int timer;
		
		Node(int n, int r, int c, int timer) {
			this.n = n;
			this.r = r;
			this.c = c;
			this.timer = timer;
		}

		@Override
		public int hashCode() {
			return Objects.hash(c, n, r, timer);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return n == other.n && r == other.r && c == other.c;
		}
		
		
	}
	
	static int[][] DIR = { {0,0,1}, {0,0,-1}, {0,1,0}, {0,-1,0}, {1,0,0}, {-1,0,0} };
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, R, C, ANS;
	static Node stx, etx;
	static char[][][] map;
	static boolean[][][] iv;
	static Queue<Node> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
	
		while (true) {

			if (!init()) break;
			
			ANS = bfs();
			
			if (ANS==-1) sb.append("Trapped!").append('\n');
			else sb.append("Escaped in ").append(ANS).append(" minute(s).").append('\n'); 
		}
		System.out.println(sb);
	}
	
	private static int bfs() {
		Q.clear();
		Q.offer(stx);
		iv[stx.n][stx.r][stx.c] = true;
		
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nn = cur.n + dir[0];
				int nr = cur.r + dir[1];
				int nc = cur.c + dir[2];
				
				if (!isValid(nn, nr, nc)) continue;
				
				if(map[nn][nr][nc]=='E') return cur.timer+1;
				
				iv[nn][nr][nc] = true;
				Q.offer(new Node(nn, nr, nc, cur.timer+1));
			}
		}
		
		return -1;
	}
	
	private static boolean isValid(int nn, int nr, int nc) {
		return 0<=nn && nn<N && 0<=nr && nr<R && 0<=nc && nc<C && !iv[nn][nr][nc] && map[nn][nr][nc] != '#'; 
	}
	
	private static boolean init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		if (N==0 && R==0 && C==0) return false;
		
		iv = new boolean[N][R][C];
		map = new char[N][R][C];
		for (int n = 0; n < N; n++) {
			
			for (int r = 0; r < R; r++) {
				String in = br.readLine();
				for (int c = 0; c < C; c++) {
					char ch = in.charAt(c);
					map[n][r][c] = ch;
					if (ch=='S') stx = new Node(n, r, c, 0);
					if (ch=='E') etx = new Node(n, r, c, 0);
				}
			}
			
			br.readLine();
		}
		
		return true;
	}
}
