import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static int[] arr, noc;
	
	public static void main(String[] args) throws IOException {
		init();
		combination(0, 0);
		System.out.println(sb);
	}
	
	private static void combination(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(noc[i]).append(' ');
			}sb.append('\n');
			return ;
		}
		
		int prev = -1;
		for (int i = start; i < N; i++) {
			if (prev == arr[i]) continue;
			noc[depth] = arr[i];
			prev = arr[i];
			combination(depth+1, i);
		}
		
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; noc = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
	}
}


