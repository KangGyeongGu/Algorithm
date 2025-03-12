import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] ANS = new int[3];
	static int[][] sheet;
	
	public static void main(String[] args) throws IOException {
		init();
		divideAndConquerRecursive(0, 0, N);
		for (int i = 0; i < 3; i++) {
			System.out.println(ANS[i]);
		}
	}
	
	private static void divideAndConquerRecursive(int r, int c, int n) {
		if (isValid(r, c, n)) {
			ANS[sheet[r][c]+1]++;
			return;
		}
		
		int range = n / 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				divideAndConquerRecursive(r + i * range, c + j * range, range);
			}
		}
	}
	
	private static boolean isValid(int r, int c, int n) {
		for (int i = r; i < r+n; i++) {
			for (int j = c; j < c+n; j++) {
				if (sheet[r][c] != sheet[i][j]) return false;
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