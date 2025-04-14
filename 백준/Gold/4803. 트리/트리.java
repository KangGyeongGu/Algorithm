import java.util.*;

public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 1) + (n << 3) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == 0 || rootB == 0) {
			parent[rootA] = parent[rootB] = 0;
			return;
		}

		if (rootA == rootB) {
			parent[rootA] = 0; 
		} else {
			parent[rootB] = rootA;
		}
	}

	static int N, M, caseCount = 1;
	static int[] parent;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		while ((N = read()) != 0 | (M = read()) != 0) {

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) parent[i] = i;

			for (int i = 0; i < M; i++) {
				int u = read(), v = read();
				union(u, v);
			}

			Set<Integer> treeRoots = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				int root = find(i);
				if (root > 0) treeRoots.add(root);
			}

			sb.append("Case ").append(caseCount++).append(": ");
			int count = treeRoots.size();
			if (count == 0) {
				sb.append("No trees.\n");
			} else if (count == 1) {
				sb.append("There is one tree.\n");
			} else {
				sb.append("A forest of ").append(count).append(" trees.\n");
			}
		}

		System.out.print(sb);
	}
}
