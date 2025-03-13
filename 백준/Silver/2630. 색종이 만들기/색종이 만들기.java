import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] ANS = new int[2];
	static int[][] sheet;
	
	public static void main(String[] args) throws IOException {
		init();
		recursive(0, 0, N);
		System.out.println(ANS[0]);
		System.out.println(ANS[1]);
	}
	
	private static void recursive(int row, int col, int n) {
		if (isSheet(row, col, n)) {
			ANS[sheet[row][col]]++;
			return;
		}
		
		int half = n / 2;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				recursive(row + i * half, col + j * half, half);
			}
		}
	}
	
	private static boolean isSheet(int row, int col, int n) {
		for (int i = row; i < row+n; i++) {
			for (int j = col; j < col+n; j++) {
				if (sheet[i][j] != sheet[row][col]) return false;
			}
		}
		return true;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		sheet = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sheet[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}