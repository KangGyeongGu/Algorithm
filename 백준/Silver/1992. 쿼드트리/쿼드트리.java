import java.io.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[][] pixels;
	
	public static void main(String[] args) throws IOException {
		init();
		recursive(0, 0, N);
		System.out.println(sb);
	}
	
	private static void recursive(int row, int col, int n) {
		if (isPixel(row, col, n)) {
			sb.append(pixels[row][col]);
			return;
		}
		
		int half = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				recursive(row + i*half, col + j*half, half);
			}
		}
		sb.append(')');
	}
	
	private static boolean isPixel(int row, int col, int n) {
		for (int i = row; i < row + n; i++) {
			for (int j = col; j < col + n; j++) {
				if (pixels[row][col] != pixels[i][j]) {
					sb.append('(');
					return false;
				}
			}
		} return true;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		
		N = Integer.parseInt(br.readLine());
		
		pixels = new int[N][N];
		for (int i = 0; i < N; i++) {
			in = br.readLine();
			for (int j = 0; j < N; j++) {
				pixels[i][j] = in.charAt(j)-'0';
			}
		}
	}
}