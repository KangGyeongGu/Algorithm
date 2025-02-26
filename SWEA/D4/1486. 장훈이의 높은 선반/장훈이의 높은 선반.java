import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, B, min;
	static int[] heights;
	static boolean[] iv;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			run();
		}
		System.out.println(sb);
        br.close();
	}
	
	// test case run,
	private static void run() {
		subSet(0);
		sb.append(min).append('\n');
	}

	// make all subset of employees' height combination,
	private static void subSet(int depth) {
		if (depth == N) {
			int curHeight = heightSum();
			if (curHeight >= B) updateMinDiff(curHeight);
			return ;
		}
		
		iv[depth] = true;
		subSet(depth+1);
		
		iv[depth] = false;
		subSet(depth+1);
	}
	
	// calculate each subsets' height sum,
	private static int heightSum() {
		int sum = 0;
		for (int i = 0; i < iv.length; i++) if (iv[i]) sum += heights[i];
		return sum;
	}
	
	// update minimum difference between shelfs' height B and employee's heights sum,
	private static void updateMinDiff(int heightSum) {
		int curdiff = Math.abs(B-heightSum);
		min = Math.min(min, curdiff);
	}
	
	// initialize test case
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		heights = new int[N]; iv = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) heights[i] = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
	}
}
