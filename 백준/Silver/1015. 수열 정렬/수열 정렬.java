import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[2][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int e = Integer.parseInt(st.nextToken());
			A[0][i] = e; A[1][i] = e;   
		}
		
		Arrays.sort(A[1]);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (A[0][i]== A[1][j]) {
					sb.append(j).append(" ");
					A[1][j] = -1;
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
