import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int T, N, sum;
	static String[] arr;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			sum = 0;
			
			for (int i = 0; i < N; i++) {
				arr = br.readLine().split("");
				
				int mid = N/2;
				
				if (i < mid) {
					for (int j = mid-i; j <= mid+i; j++) {
						sum += Integer.parseInt(arr[j]);
					}
				}
				
				if (i==mid) {
					for (int j = 0; j < N; j++) {
						sum += Integer.parseInt(arr[j]);
					}
				}
				
				if (i > mid) {
					for (int j = mid-(N-1-i); j <= mid + N-1-i; j++) {
						sum += Integer.parseInt(arr[j]);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
