import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] DIR = { {0,1}, {1,0}, {-1,0}, {0,-1} };
	
	static int N, M, lighted;
	static int[][] barn;
	static boolean[][] iV;
	static Map<Integer, List<Integer>> bulb;
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		barn = new int[N+1][N+1];
		iV = new boolean[N+1][N+1];
		bulb = new HashMap<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
				
			bulb.computeIfAbsent(x*(N+1)+y, k -> new ArrayList<>()).add(a*(N+1)+b);
		}
	}
	
	private static void bfs() {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(1*(N+1)+1);
		barn[1][1] = 1;
		iV[1][1] = true;
		lighted = 1;
		
		while (!Q.isEmpty()) {
			
			int cur = Q.poll();
			int cx = cur / (N+1), cy = cur % (N+1);
			
			if (bulb.containsKey(cur)) {
				
				// 1. light on 
				for (int room : bulb.get(cur)) { 
					int rx = room / (N+1), ry = room % (N+1);
					
					if (barn[rx][ry] == 0) {
						barn[rx][ry] = 1;
						lighted++;
					}
					
					for (int[] dir : DIR) {
						int tx = rx + dir[0], ty = ry + dir[1];
						if (1 <= tx && tx <= N && 1 <= ty && ty <= N && iV[tx][ty] && !iV[rx][ry]) {
							Q.offer(rx * (N + 1) + ry);
							iV[rx][ry] = true;
							break;
						}
					}
				}
				
				bulb.get(cur).clear(); 
			}
			
			for (int[] dir : DIR) {
				int nx = cx + dir[0], ny = cy + dir[1], next = nx * (N+1) + ny;
				
				if (N+1 <= nx || nx < 1 || N+1 <= ny || ny < 1 || iV[nx][ny] || barn[nx][ny] == 0) continue;
				
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
