import java.util.*;

public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<1) + (n<<3) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	static class Coords {
		int x, y;
		Coords(int x, int y) { this.x = x; this.y = y; }
	}
	
	static class Bridge implements Comparable<Bridge> {
		int from, to, bridgeLen;

		public Bridge(int from, int to, int bridgeLen) {
			this.from = from;
			this.to = to;
			this.bridgeLen = bridgeLen;
		}
		
		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.bridgeLen, o.bridgeLen);
		}
	}
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	static int N, M, map[][], set[], islandCount = 1;
	static boolean[][] iv;
	static Queue<Coords> Q;
	static PriorityQueue<Bridge> pQ;
	
	// 1. 테스트케이스 초기화
	private static void init() throws Exception {
		N = read(); M = read(); map = new int[N][M];
		for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) map[i][j] = read(); 
		iv = new boolean[N][M];
	}
	
	private static boolean isArrayOutOfIndex(int nx, int ny) {
		return N <= nx || nx < 0 || M <= ny || ny < 0; 
	}
	
	// 2. 섬 판별 BFS
	private static void bfs(Coords stx, int idx) {
		Q = new ArrayDeque<>();
		Q.offer(stx);
		iv[stx.x][stx.y] = true;
		map[stx.x][stx.y] = idx;
		
		while (!Q.isEmpty()) {
			Coords cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (isArrayOutOfIndex(nx, ny) || iv[nx][ny] || map[nx][ny] == 0) continue;
				
				iv[nx][ny] = true;
				map[nx][ny] = idx;
				Q.offer(new Coords(nx, ny));
			}
		}
	}
	
	// 3. 지도 내 모든 섬 번호 인덱싱
	private static void IslandIndexing() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (iv[i][j] || map[i][j] == 0) continue;
				
				bfs(new Coords(i, j), islandCount++);
			}
		}
	}
	
	// 4. 모든 섬 간 다리 연결
	private static void bridgeConnection() {
		pQ = new PriorityQueue<>();
		
		for (int x = 0; x < N; x++) { // 지도 내 모든 점에서 시도
			for (int y = 0; y < M; y++) {
				if (map[x][y] == 0) continue; // 바다이면 시도하지 않고,
				
				int from = map[x][y]; // 출발점에서 시작해서,
				
				for (int[] dir : DIR) { // 각 방향에 대해서,
					int nx = x + dir[0], ny = y + dir[1], bridgeLen = 0;
					
					while (!isArrayOutOfIndex(nx, ny)) { // 지도를 벗어나지 않을 때 까지,
						if (map[nx][ny] == from) break;
						
						if (map[nx][ny] != 0) {
							if (bridgeLen >= 2) {
								int to = map[nx][ny];
								pQ.offer(new Bridge(from, to, bridgeLen));
							}
							break;
						}
						
						nx += dir[0]; // 이전과 같은 방향으로 계속해서 탐색한다.
						ny += dir[1];
						bridgeLen++;
					}
				}
			}
		}
	}
	
	// 5. 모든 섬에 대한 최소 연결 (MST)
	private static void make() {
		set = new int[islandCount];
		for (int i = 1; i < islandCount; i++) set[i] = i;
	}
	
	private static int find(int x) {
		if (set[x] == x) return x;
		return set[x] = find(set[x]);
	}
	
	private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        set[b] = a;
        return true;
    }
	
	private static void makeMST() {
		make();
		
		int sum = 0, count = 0;
		
		while (!pQ.isEmpty()) {
			Bridge bridge = pQ.poll();
			
			if (union(bridge.from, bridge.to)) {
				sum += bridge.bridgeLen;
				count++;
			}
		}
		
		System.out.println(((count == islandCount-2) ? sum : -1));
	}
	
	public static void main(String[] args) throws Exception {
		init();
		IslandIndexing();
		bridgeConnection();
		makeMST();
	}
}
