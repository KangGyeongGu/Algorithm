import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int T, colorBlindCount, normalCount;
	static int[][] rgb;
	static boolean[][] iV;
	static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		init();
		
		runNormalBfs();
		
		runColorBlindBfs();
		
		System.out.println(normalCount + " " + colorBlindCount);
	}
	
	private static void runNormalBfs() {
		iV = new boolean[T][T];
		
		for (int r = 0; r < T; r++) {
			for (int c = 0; c < T; c++) {
				if (rgb[r][c]==R && !iV[r][c]) normalBfs(r, c);
				
				else if (rgb[r][c]==G && !iV[r][c]) normalBfs(r, c);
				
				else if (rgb[r][c]==B && !iV[r][c]) normalBfs(r, c);
				
			}
		}
	}
	
	private static void runColorBlindBfs() {
		iV = new boolean[T][T];
		
		for (int r = 0; r < T; r++) {
			for (int c = 0; c < T; c++) {
				if (rgb[r][c]==R && !iV[r][c]) colorBlindBfs(r, c);
				
				else if (rgb[r][c]==G && !iV[r][c]) colorBlindBfs(r, c);
				
				else if (rgb[r][c]==B && !iV[r][c]) colorBlindBfs(r, c);
			}
		}
	}
	
	private static void normalBfs(int x, int y) {
		q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		iV[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] curP = q.poll(); int cX = curP[0]; int cY = curP[1];
			
			for (int i = 0; i < 4; i++) {
				int nX = cX + deltas[i][0];
				int nY = cY + deltas[i][1];
				
				if (!isValidRange(nX, nY) || !isSameColor(cX, cY, nX, nY)) continue;
				
				iV[nX][nY] = true;
				q.offer(new int[] {nX, nY});
			}
		}
		normalCount++;
	}
	
	private static boolean isSameColor(int x, int y, int nX, int nY) {
		int curP = rgb[x][y]; int nextP = rgb[nX][nY];
		if (curP == nextP) return true;
		else return false;
	}
	
	private static void colorBlindBfs(int x, int y) {
		q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		iV[x][y] = true; 
		
		while (!q.isEmpty()) {
			
			int[] curP = q.poll(); int cX = curP[0]; int cY = curP[1];
			
			for (int i = 0; i < 4; i++) {
				int nX = cX + deltas[i][0];
				int nY = cY + deltas[i][1];
				
				if (!isValidRange(nX, nY) || isSeperateColor(cX, cY, nX, nY)) continue;
				
				iV[nX][nY] = true;
				q.offer(new int[] {nX, nY});
			}
		}
		colorBlindCount++;
	}
	
	private static boolean isSeperateColor(int x, int y, int nX, int nY) {
		int curP = rgb[x][y]; int nextP = rgb[nX][nY];
		
		if (curP == B && (nextP == R || nextP == G))  return true;
		else if ((curP == R || curP == G) && nextP == B) return true;
		else return false;
	}
	
	private static boolean isValidRange(int x, int y) {
		if (x < 0 || x >= T || y < 0 || y >= T || iV[x][y]) return false;
		else return true;
	}
	
	private static void init() throws IOException {
		T = Integer.parseInt(br.readLine());
		
		rgb = new int[T][T];
		
		for (int r = 0; r < T; r++) {
			String in = br.readLine();
			for (int c = 0; c < T; c++) {
				rgb[r][c] = mapColor(in.charAt(c)); 
			}
		}
	}
	
	private static int mapColor(char ch) {
		if (ch == 'R') return R;
		else if (ch == 'G') return G;
		else return B;
	}
	
}