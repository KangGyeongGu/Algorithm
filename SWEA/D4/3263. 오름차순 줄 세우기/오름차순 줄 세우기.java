import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, arr[], pos[];
	
	private static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
	
	private static int findMaxLength() {
		pos = new int[N+2];
		
		int maxLength = 0;
		
		for (int i = 0; i < N; i++) {
			int now = arr[i];
			pos[now] = pos[now - 1] + 1;
			maxLength = Math.max(maxLength, pos[now]);
		}
		
		return maxLength;
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(N - findMaxLength()).append("\n");
		}
		
		System.out.println(sb);	
	}
}
