import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static int[] set, noc;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		set = new int[N]; noc = new int[M];
		int idx = 0;
		for (int i = 1; i <= N; i++) set[idx++] = i;
		
		combination(0, 0);
		System.out.println(sb);
	}
	
	private static void combination(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(noc[i]).append(" ");
			}sb.append('\n');
			return ;
		}
		
		int prev = -1;
		for (int i = start; i < N; i++) {
			if (prev == set[i]) continue;
			noc[depth] = set[i];
			prev = set[i];
			combination(depth+1, i);
		}
	}
}