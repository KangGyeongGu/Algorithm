import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][] DIR = { {0,1}, {1,0}, {-1,0}, {0,-1} }; 
	
	static int N, M, SIZE, lighted;
	static boolean[][] isLighted, iV;   
	static Map<Integer, List<Integer>> bulbs;

	// 초기화 메서드
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());  
		M = Integer.parseInt(st.nextToken()); 

		SIZE = N + 1; 

		// 불 켜짐 여부, 방문 여부 배열 초기화
		isLighted = new boolean[SIZE][SIZE];  
		iV = new boolean[SIZE][SIZE];
		bulbs = new HashMap<>(); 

		// 스위치 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());  
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());  
			int b = Integer.parseInt(st.nextToken());

			bulbs.computeIfAbsent(compressCoordinate(x, y), k -> new ArrayList<>()).add(compressCoordinate(a, b));
		}
	}
	
	private static int compressCoordinate(int x, int y) {
		return x * SIZE + y; 
	}
	
	private static void bfs() {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(compressCoordinate(1, 1));  
		isLighted[1][1] = true; 
		iV[1][1] = true;
		lighted = 1;

		while (!Q.isEmpty()) {
			int cur = Q.poll();  
			int cx = cur / SIZE;  
			int cy = cur % SIZE;  

			if (bulbs.containsKey(cur)) {
				for (int room : bulbs.get(cur)) { 
					int rx = room / SIZE; 
					int ry = room % SIZE; 

					if (!isLighted[rx][ry]) { 
						isLighted[rx][ry] = true;  
						lighted++; 
					}

					for (int[] dir : DIR) {
						int tx = rx + dir[0], ty = ry + dir[1];
						if (1 <= tx && tx <= N && 1 <= ty && ty <= N && iV[tx][ty] && !iV[rx][ry]) {
							Q.offer(compressCoordinate(rx, ry)); 
							iV[rx][ry] = true; 
							break;
						}
					}
				}
				bulbs.get(cur).clear();  
			}

			
			for (int[] dir : DIR) {
				int nx = cx + dir[0], ny = cy + dir[1];
				int next = compressCoordinate(nx, ny); 

				if (nx < 1 || nx > N || ny < 1 || ny > N || iV[nx][ny] || !isLighted[nx][ny]) continue;

				iV[nx][ny] = true;
				Q.offer(next);
			}
		}

		System.out.println(lighted);
	}

	public static void main(String[] args) throws Exception {
		init(); 
		bfs(); 
	}
}
