import java.io.*;
import java.util.*;

public class Main {

	static int N, max;
	static int[][] triangle;
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
		System.out.println(triangle[N-1][N-1]);
	}
	
	private static void dp() {
		for (int i = 1; i < N; i++) {
			triangle[i][0] += triangle[i-1][0];
			triangle[i][i] += triangle[i-1][i-1];
			
			for (int j = 1; j < i; j++) {
				triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
			}
		}
        
		Arrays.sort(triangle[N-1]);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		triangle = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i+1; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = triangle[0][0];
	}
}
