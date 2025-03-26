import java.util.*;
import java.io.*;

public class Solution {

	static int T, N, M;
	static int[] sets;
	static Set<Integer> result;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			countGroup();
			sb.append("#" + tc + " " + result.size() + '\n');
		}
		
		System.out.println(sb);
	}
	
	private static int countGroup() {
		for (int i = 1; i <= N; i++) {
			result.add(find(i));
		}
		return result.size();
	}
	
	private static void init(int tc) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sets = new int[N+1];
		for (int i = 0; i <= N; i++) sets[i] = -1;
		
		result = new HashSet<>();
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			union(u, v);
		}
	}
	
	private static int find(int x) {
		if (sets[x] < 0) return x;
		return sets[x] = find(sets[x]);
	}
	
	private static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if (u == v) return false;
		
		if (sets[u] < sets[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		if (sets[u] == sets[v]) sets[u]--;
		
		sets[v] = u;
		
		return true;
	}
}