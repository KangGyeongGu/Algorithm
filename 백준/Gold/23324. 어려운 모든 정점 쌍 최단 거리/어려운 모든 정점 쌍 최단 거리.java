public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48) n = (n << 1) + (n << 3) + (c & 15);
		if (c == 13) System.in.read(); return n;
	}

	static int find(int x) {
		if (x != parent[x])
			parent[x] = find(parent[x]);
		return parent[x];
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return;

		if (size[rootA] < size[rootB]) {
			parent[rootA] = rootB;
			size[rootB] += size[rootA];
		} 
		else {
			parent[rootB] = rootA;
			size[rootA] += size[rootB];
		}
	}

	static int[] parent, size;
	static int N, M, K;

	public static void main(String[] args) throws Exception {

		N = read(); M = read(); K = read();

		parent = new int[N + 1]; 
		size = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		int[][] edges = new int[M + 1][2];

		for (int i = 1; i <= M; i++) {
			int u = read(), v = read();
			edges[i][0] = u;
			edges[i][1] = v;

			if (i != K) union(u, v);
		}

		int u = edges[K][0],  v = edges[K][1];

		int rootU = find(u),  rootV = find(v);

		long ans = 0;
		if (rootU != rootV) {
			long a = size[rootU], b = size[rootV];
			ans = a * b;
		}

		System.out.println(ans);
	}
}