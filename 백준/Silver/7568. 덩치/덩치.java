import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			int rank = 1;
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][0] < arr[j][0]) {
					if (arr[i][1] < arr[j][1]) {
						rank++;
					}
				}
				else continue;
			}
			sb.append(rank).append(" ");
		}
		
		System.out.println(sb);
	}
}