import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static int[] arr, noc;
	static boolean[] iv;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; iv = new boolean[N];
		noc = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		permutation(0);
		System.out.println(sb);
		br.close();
	}
	
	private static void permutation(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(noc[i]).append(' ');
			} sb.append('\n');
			return ;
		}
		
		int prev = -1;
		for (int i = 0; i < N; i++) {
			if (iv[i] || arr[i]==prev) continue;
			iv[i] = true;
			noc[depth] = arr[i];
			prev = arr[i];
			permutation(depth+1);
			iv[i] = false;
		}
	}
}