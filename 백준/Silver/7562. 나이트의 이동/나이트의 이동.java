import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, l;
	static int[][] B;
	static boolean[][] iV;
	static int[][] P = new int[2][2];
	
	static int[][] deltas = { {-2,-1}, {-1,-2}, {-2,1}, {-1,2}, {1,2}, {2,1}, {1,-2}, {2,-1} };
	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			init();
			
			bfs(P[0][0], P[0][1]);
			
			sb.append(B[P[1][0]][P[1][1]]).append('\n');
		}
		System.out.println(sb);
		br.close();
	}
	
	private static void bfs(int x, int y) {
		q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] curP = q.poll();
			
			for (int i = 0; i < 8; i++) {
				int nX = curP[0] + deltas[i][0];
				int nY = curP[1] + deltas[i][1];
				
				if (!isValidPosition(nX, nY) || iV[nX][nY]) continue;
				
				iV[nX][nY] = true;
				q.offer(new int[] {nX, nY});
				B[nX][nY] = B[curP[0]][curP[1]] + 1; 
			}
		}
	}
	
	private static boolean isValidPosition(int x, int y) {
		if (x < 0 || x >= l || y < 0 || y >= l) return false;
		return true;
	}
	
	private static void init() throws IOException {
		l = Integer.parseInt(br.readLine());
		iV = new boolean[l][l];
		
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				P[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		B = new int[l][l];
		iV[P[0][0]][P[0][1]] = true;
	}
}