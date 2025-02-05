import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] NM = new int[N][M];
		for (int i = 0; i < NM.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < NM[0].length; j++) {
				NM[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		int[][] scope = new int[K][4];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				scope[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for (int i = 0; i < K; i++) {
			System.out.println(getSum(NM, scope[i]));
		}
		
		br.close();
	}
	
	static int getSum(int[][] NM, int[] scope) {
		int sum = 0;
		for (int r = scope[0]-1; r < scope[2]; r++) {
			for (int c = scope[1]-1; c < scope[3]; c++) {
				sum += NM[r][c];
			}
		}
		return sum;
	}
}
