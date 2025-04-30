import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, F;
	static int index, parent[], size[];
	static Map<String, Integer> map;

	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());

		while (N-- > 0) {

			F = Integer.parseInt(br.readLine());

			parent = new int[F * 2];
			size = new int[F * 2];
			map = new HashMap<>();
			index = 0;

			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				size[i] = 1;
			}

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (!map.containsKey(a))
					map.put(a, index++);
				if (!map.containsKey(b))
					map.put(b, index++);

				int rootA = find(map.get(a));
				int rootB = find(map.get(b));

				if (rootA != rootB) {
					parent[rootB] = rootA;
					size[rootA] += size[rootB];
				}

				sb.append(size[find(rootA)]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}