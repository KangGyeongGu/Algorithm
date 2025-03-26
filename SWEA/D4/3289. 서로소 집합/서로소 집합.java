import java.util.*;
import java.io.*;

public class Solution {

	static int T, N, M;
	static int[] sets;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int UNION = 0, FIND = 1;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sets = new int[N+1];
			for (int i = 0; i <= N; i++) {
				sets[i] = -1;
			}
			
			while (M-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				int op = Integer.parseInt(st.nextToken());
				int s1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				
				if (op == UNION) {
					union(s1, s2);
				}
				else if (op == FIND) {
					if (isSameSet(s1, s2)) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
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
	
	private static boolean isSameSet(int u, int v) {
		u = find(u);
		v = find(v);
		
		if (u == v) return true;
		return false;
	}
}
