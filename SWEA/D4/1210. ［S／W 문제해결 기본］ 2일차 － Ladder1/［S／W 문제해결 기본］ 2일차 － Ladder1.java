import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int tc;
	static int[] dPos = new int[2];
	static int[][] ladder = new int[100][100];
	
	static final int [][] DIR = { {0,-1}, {0,1} }; // L R
	
	public static void main(String[] args) throws IOException {

		for (int tc = 1; tc <= 10; tc++) {
			init();
			reverseRouting();
		}
		System.out.println(sb);
	}
	
	private static void reverseRouting() {
		int curC = dPos[1];
		int[] curD = new int[2];
		boolean flag;
		
		for (int i = 99; i >= 0; i--) { // up going,,
			flag = false;
			
			for (int[] dir : DIR) { // LR search,
				int nc = curC + dir[1];
				if (isValidDirection(i, nc)) { // if L or R exists,
					curD = dir;
					flag = true;
					break;
				}
			}
			
			// if LR found,
			if (flag) {
				while (isValidMovement(i, curC, curD)) { // move to the direction till ends
					curC += curD[1]; // change column index
				}
			}
		}
		sb.append(curC).append("\n");
	}
	
	private static boolean isValidDirection(int height, int nc) {
		return 0<=nc && nc<100 && ladder[height][nc] == 1;
	}
	
	private static boolean isValidMovement(int height, int curC, int[] curD) {
		return 0<=(curC + curD[1]) && (curC + curD[1]) < 100 && ladder[height][curC + curD[1]] != 0;
	}
	
	private static void init() throws IOException {
		tc = Integer.parseInt(br.readLine());
		sb.append("#").append(tc).append(" ");
		
		for (int r = 0; r < 100; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 100; c++) {
				ladder[r][c] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for (int c = 0; c < 100; c++) { // find destination coordinates
			if (ladder[99][c] == 2) {
				dPos[0] = 99;
				dPos[1] = c;
				break;
			}
		}
	}
}
