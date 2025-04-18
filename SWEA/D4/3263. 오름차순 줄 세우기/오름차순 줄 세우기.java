import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, arr[], pos[];
	
	private static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
	
	private static int lis() {
		pos = new int[N+1];
		for (int i = 0; i < N; i++) pos[arr[i]] = i;
		
		int maxSeq = 1, cur = 1;
		
		for (int i = 2; i <= N; i++) {
			if (pos[i-1] < pos[i]) cur++;
			else {
				maxSeq = Math.max(maxSeq, cur);
				cur = 1;
			}
		}
		
		return maxSeq = Math.max(maxSeq, cur);
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(N-lis()).append("\n");
		}
		System.out.println(sb);	
	}
}
