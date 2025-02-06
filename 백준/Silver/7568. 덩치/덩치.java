import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 2; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			int rank = 1;
			for (int j = 0; j < arr.length; j++) {
				if (i == j) continue;
				if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) rank++;
			}
			sb.append(rank).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
}
