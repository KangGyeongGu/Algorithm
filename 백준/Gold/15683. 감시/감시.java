import java.util.*;
import java.io.*;

public class Main {

	static class CCTV {
		int x, y, num;
		public CCTV(int x, int y, int num) { this.x = x; this.y = y; this.num = num; }
	}
	
	static int N, M, blindArea;
	static int[][] office;
	static boolean[][] iv;
	static List<CCTV> cctvList = new ArrayList<>();

	static int[] noc;
	static final int[][] DIR = { {0,1}, {1,0}, {0,-1}, {-1,0} };
	static final int CHECKED = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		init();
		dupPermute(0);
		System.out.println(blindArea);
	}
	
	
	private static void dupPermute(int depth) {
		if (depth == cctvList.size()) {
			cctvSimulate();
			return ;
		}
		
		for (int i = 0; i < 4; i++) { // 4-direction
			noc[depth] = i;
			dupPermute(depth+1);
		}
	}
	
	
	private static void cctvSimulate() {
		
		int[][] nocMap = mapCopy(); // temporary office map,
		
		for (int i = 0; i < cctvList.size(); i++) {
			
			int cr = cctvList.get(i).x;
			int cc = cctvList.get(i).y;
			int direction = noc[i];
			
			int curCCTV = cctvList.get(i).num;
			
			switch (curCCTV) {
				case 1:
					nocMap = cctvActivate(nocMap, direction, cr, cc);
					break;
					
				case 2:
					nocMap = cctvActivate(nocMap, direction, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+2)%4, cr, cc);
					break;
					
				case 3:
					nocMap = cctvActivate(nocMap, direction, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+1)%4, cr, cc);
					break;
					
					
				case 4:
					nocMap = cctvActivate(nocMap, direction, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+1)%4, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+2)%4, cr, cc);
					break;
					
					
				case 5:
					nocMap = cctvActivate(nocMap, direction, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+1)%4, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+2)%4, cr, cc);
					nocMap = cctvActivate(nocMap, (direction+3)%4, cr, cc);
					break;
			}
		}
		
		calcBlindArea(nocMap);
	}
	
	
	private static int[][] cctvActivate(int[][] nocMap, int dir, int cr, int cc) {
		
		while (true) {
			cr += DIR[dir][0];
			cc += DIR[dir][1];
			
			if (N <= cr || cr < 0 || M <= cc || cc < 0 || nocMap[cr][cc] == 6) break;
			
			if (nocMap[cr][cc] != 0) continue;
			
			nocMap[cr][cc] = CHECKED;
			
		}
		
		return nocMap;
	}
	
	
	private static int[][] mapCopy() {
		int[][] copyTo = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copyTo[r][c] = office[r][c];
			}
		}
		return copyTo;
	}
	
	
	private static void calcBlindArea(int[][] nocMap) {
		int area = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (nocMap[r][c] == 0) area++;
			}
		}
		blindArea = Math.min(blindArea, area);
	}
	
	
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int in = Integer.parseInt(st.nextToken());
				office[r][c] = in;
				
				if (1 <= in && in <= 5) cctvList.add(new CCTV(r, c, in));
			}
		}
		
		noc = new int[cctvList.size()];
		blindArea = Integer.MAX_VALUE;
	}
}
