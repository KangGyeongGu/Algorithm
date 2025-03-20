import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr, noc;
	static boolean[] iv;

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = sc.nextInt(); 
		sc.close();
		
		noc = new int[M];
		
		Arrays.sort(arr);
		permutation(0);
		System.out.println(sb);
	}
	
	private static void permutation(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) sb.append(noc[i] + " ");
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < N; i++) {
			noc[depth] = arr[i];
			permutation(depth+1);
		}
	}
}