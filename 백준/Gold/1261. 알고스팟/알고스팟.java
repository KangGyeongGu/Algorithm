import java.util.*;
import java.io.*;

public class Main {
	
	static class Room implements Comparable<Room>{
		int x, y, breakCount;

		public Room(int x, int y, int breakCount) {
			this.x = x;
			this.y = y;
			this.breakCount = breakCount;
		}
		
		@Override
		public int compareTo(Room o) {
			return Integer.compare(this.breakCount, o.breakCount);
		}
	}
	
	static int M, N, maze[][], dist[][];
	static boolean[][] visited;
	static PriorityQueue<Room> pQ;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[M][N];
		maze = new int[M][N];
		for (int i = 0; i < M; i++) {
			dist[i] = new int[N];
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			
			String in = br.readLine();
			for (int j = 0; j < N; j++) {
				maze[i][j] = in.charAt(j) - '0';
			}
		}
	}
	
	
	private static int dijkstra() {
		pQ = new PriorityQueue<>();
		visited = new boolean[M][N];
		pQ.offer(new Room(0, 0, 0));
		visited[0][0] = true;
		dist[0][0] = 0;
		
		while (!pQ.isEmpty()) {
			Room cur = pQ.poll();
			
			if (cur.x == M-1 && cur.y == N-1) return cur.breakCount;
			
			if (dist[cur.x][cur.y] < cur.breakCount) continue;
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (nx < 0 || ny < 0 || M <= nx || N <= ny || visited[nx][ny]) continue;
				
				int newBreakCount = cur.breakCount + maze[nx][ny];
				
				if (newBreakCount < dist[nx][ny]) {
					dist[nx][ny] = newBreakCount;
					pQ.offer(new Room(nx, ny, newBreakCount));
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(dijkstra());
	}
}