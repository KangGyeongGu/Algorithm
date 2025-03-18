import java.util.*;
import java.io.*;

public class Main {

	static class Node {
		int r; 
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static char[][] map;
	static int[][] cluster;
	static Map<Integer, Integer> clusterMap = new HashMap<>();
	
	static Queue<Node> Q;
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		init();
		classification();
		simulation();
	}
	
	private static void simulation() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(wallCount(r, c));
			} sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static int wallCount(int r, int c) {
		if (map[r][c]=='0') return 0;
		
		Set<Integer> clusterSet = new HashSet<>();
		int clusterSize = 1;
		
		for (int[] dir : DIR) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			
			if (isOutOfArray(nr, nc) || cluster[nr][nc] == 0) continue;
			
			clusterSet.add(cluster[nr][nc]);	
		}
		
		for (int clusterIndex : clusterSet) {
			clusterSize += clusterMap.get(clusterIndex);
		}
		
		return clusterSize % 10;
	}
	
	private static void classification() {
		cluster = new int[N][M];
		int clusterIdx = 1;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == '0' && cluster[r][c] == 0) {
					clusterMap.put(clusterIdx, bfs(r, c, clusterIdx));
					clusterIdx++;
				}
			}
		}
	}
	
	private static int bfs(int r, int c, int clusterIdx) {
		Q = new ArrayDeque<>();
		Q.offer(new Node(r, c));
		cluster[r][c] = clusterIdx;
		
		int clusterSize = 1;
		
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nr = cur.r + dir[0];
				int nc = cur.c + dir[1];
				
				if (isOutOfArray(nr, nc) || isNotCluster(nr, nc)) continue;
				
				Q.offer(new Node(nr, nc));
				cluster[nr][nc] = clusterIdx;
				clusterSize++;
			}
		}
		
		return clusterSize;
	}
	
	private static boolean isOutOfArray(int nr, int nc) {
		return N <= nr || nr < 0 || M <= nc || nc < 0;
	}
	
	private static boolean isNotCluster(int nr, int nc) {
		return map[nr][nc] != '0' || cluster[nr][nc] != 0; 
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
	}
}