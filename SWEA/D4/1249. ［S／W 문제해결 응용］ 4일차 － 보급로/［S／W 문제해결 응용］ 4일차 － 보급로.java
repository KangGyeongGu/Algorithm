import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static class Node implements Comparable<Node> {
    	int x, y, w; Node(int x, int y, int w) { this.x = x; this.y = y; this.w = w; }
    	@Override public int compareTo(Node o) { return Integer.compare(this.w, o.w); }
    }
    
    static int T, N;
    static int[][] map;
    static int[] dist;
    static boolean[][] iv;
    static PriorityQueue<Node> pQ;
    static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    
    private static void init(int tc) throws IOException {
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];
    	for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = in.charAt(j) - '0';
			}
		}
    	
    	iv = new boolean[N][N];
    	dist = new int[N*N];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    }
    
    private static void dijkstra() {
    	pQ = new PriorityQueue<>();
    	pQ.offer(new Node(0, 0, 0));
    	iv[0][0] = true;
    	dist[0] = 0;
    	
    	while (!pQ.isEmpty()) {
    		Node cur = pQ.poll();
    		int coord = cur.x * N + cur.y;
    		
    		if (dist[coord] < cur.w) continue;
    		
    		for (int[] dir : DIR) {
    			int nx = cur.x + dir[0];
    			int ny = cur.y + dir[1];
    			int nCoord = nx * N + ny;
    			
    			if (N <= nx || nx < 0 || N <= ny || ny < 0 || iv[nx][ny]) continue;
    			
    			if (dist[nCoord] > cur.w + map[nx][ny]) {
    				iv[nx][ny] = true;
    				dist[nCoord] = cur.w + map[nx][ny];
    				pQ.offer(new Node(nx, ny, dist[nCoord]));
    			}
    		}
    	}
    }
    
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			dijkstra();
			sb.append("#").append(tc).append(" ").append(dist[N*N-1]).append('\n');
		}
		System.out.println(sb);
	}
}
