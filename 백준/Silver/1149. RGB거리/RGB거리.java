import java.io.*;
import java.util.*;

public class Main {
	
	static final int R = 0, G = 1, B = 2;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[][] RGB;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		RGB = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				RGB[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < N; i++) {
			RGB[i][R] += Math.min(RGB[i-1][G], RGB[i-1][B]);
			RGB[i][G] += Math.min(RGB[i-1][R], RGB[i-1][B]);
			RGB[i][B] += Math.min(RGB[i-1][R], RGB[i-1][G]);
		}
		
		System.out.println(Math.min(RGB[N-1][R], Math.min(RGB[N-1][G], RGB[N-1][B])));
	}
}
