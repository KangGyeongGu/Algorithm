import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, K;
	static long[] origin, tree;
	
	
	private static long treeInit(int start, int end, int node) {
		if (start == end) return tree[node] = origin[start];
		int mid = (start+end) / 2;
		return tree[node] = treeInit(start, mid, node*2) + treeInit(mid+1, end, node*2+1);
	}
	
	private static long treeSum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) return 0;
		
		if (left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return treeSum(start, mid, node*2, left, right) + treeSum(mid+1, end, node*2+1, left, right);
	}
	
	private static void update(int start, int end, int node, int idx, long diff) {
		if (idx < start || idx > end) return;
		
		tree[node] += diff;
		if (start == end) return;
		
		int mid = (start+end)/2;
		update(start, mid, node*2, idx, diff);
		update(mid+1, end, node*2+1, idx, diff);
	}
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		origin = new long[N+1];
		for (int i = 1; i <= N; i++) {
			origin[i] = Long.parseLong(br.readLine());
		}
		
		tree = new long[N*4];
		treeInit(1, N, 1);
	}
	
	private static void prefixSum() throws Exception {
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a==1) {
				long diff = c - origin[b];
				origin[b] = c;
				update(1, N, 1, b, diff);
			}
			
			else if (a==2) {
				sb.append(treeSum(1, N, 1, b, (int) c) + "\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		prefixSum();
	}
}