import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, R;
	static int[][] ARR;
	static int[] OP;
	
	public static void main(String[] args) throws IOException {
		init();
		run();
		
	}
	
	private static void run() {
		for (int op : OP) {
			rotate(op);
		}
		print();
	}
	
	private static void rotate(int operator) {
		switch (operator) {
		case 1:
			verticalReversion();
			break;
		case 2: 
			horizontalReversion();
			break;
		case 3: 
			rotateClockWise();
			break;
		case 4: 
			rotateCounterClockWise();
			break;
		case 5: 
			rotateQuadrantClockWise();
			break;
		case 6: 
			rotateQuadrantCounterClockWise();
			break;
		}
	}
	
	// UP-DOWN reverse
	private static void verticalReversion() {
		int ROW = ARR.length; int COL = ARR[0].length;
		for (int c = 0; c < COL; c++) { // about each M cols,
			for (int i = 0, j = ROW-1; i < j; i++, j--) {
				int t = ARR[i][c];
				ARR[i][c] = ARR[j][c];
				ARR[j][c] = t;
			}
		}
	}
	
	// LEFT-RIGHT reverse
	private static void horizontalReversion() {
		int ROW = ARR.length; int COL = ARR[0].length;
		for (int r = 0; r < ROW; r++) { // about each N row,
			for (int i = 0, j = COL-1; i< j; i++, j--) {
				int t = ARR[r][i];
				ARR[r][i] = ARR[r][j];
				ARR[r][j] = t;
			}
		}
	}
	
	// CLOCKWISE rotate (degree:90)
	private static void rotateClockWise() {
		int ROW = ARR.length; int COL = ARR[0].length;
		int[][] t = new int[COL][ROW];
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				t[j][ROW-1-i] = ARR[i][j];
			}
		}
		
		ARR = t;
	}
	
	// COUNTERCLOCKWISE rotate (degree:90)
	private static void rotateCounterClockWise() {
		int ROW = ARR.length; int COL = ARR[0].length;
		int[][] t = new int[COL][ROW];
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				t[COL-1-j][i] = ARR[i][j];
			}
		}
		ARR = t;
	}
	
	// QUADRANT CLOCKWISE rotate
	private static void rotateQuadrantClockWise() {
		int ROW = ARR.length; int COL = ARR[0].length;
		
		int[][] t = new int[ROW][COL];

		// save 1st group elements in quadrant[1]
		for (int i = 0; i < ROW / 2; i++) {
	        for (int j = 0; j < COL / 2; j++) {
	            t[i][j + COL / 2] = ARR[i][j];
	        }
	    }
		
		// save 2nd group elements in quadrant[4]
	    for (int i = 0; i < ROW / 2; i++) {
	        for (int j = COL / 2; j < COL; j++) {
	            t[i + ROW / 2][j] = ARR[i][j];
	        }
	    }
		
		// save 3rd group elements in quadrant[3]
	    for (int i = ROW / 2; i < ROW; i++) {
	        for (int j = COL / 2; j < COL; j++) {
	            t[i][j - COL / 2] = ARR[i][j];
	        }
	    }
		
		// save 4th group elements in quadrant[2]
	    for (int i = ROW / 2; i < ROW; i++) {
	        for (int j = 0; j < COL / 2; j++) {
	            t[i - ROW / 2][j] = ARR[i][j];
	        }
	    }
		
		ARR = t;
	}
	
	// QUADRANT COUNTERCLOCKWISE rotate
	private static void rotateQuadrantCounterClockWise() {
		int ROW = ARR.length; int COL = ARR[0].length;
		
		int[][] t = new int[ROW][COL];
		
	    for (int i = 0; i < ROW / 2; i++) {
	        for (int j = 0; j < COL / 2; j++) {
	            t[i + ROW / 2][j] = ARR[i][j];  
	        }
	    }

	    for (int i = 0; i < ROW / 2; i++) {
	        for (int j = COL / 2; j < COL; j++) {
	            t[i][j - COL / 2] = ARR[i][j];  
	        }
	    }

	    for (int i = ROW / 2; i < ROW; i++) {
	        for (int j = COL / 2; j < COL; j++) {
	            t[i - ROW / 2][j] = ARR[i][j]; 
	        }
	    }

	    for (int i = ROW / 2; i < ROW; i++) {
	        for (int j = 0; j < COL / 2; j++) {
	            t[i][j + COL / 2] = ARR[i][j]; 
	        }
	    }
		
		ARR = t;
	}
	
	private static void print() {
		int ROW = ARR.length; int COL = ARR[0].length;
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) sb.append(ARR[i][j]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		ARR = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				ARR[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		OP = new int[R];
		st = new StringTokenizer(br.readLine());
		for (int r = 0; r < R; r++) {
			OP[r] = Integer.parseInt(st.nextToken());
		}
	}
}
