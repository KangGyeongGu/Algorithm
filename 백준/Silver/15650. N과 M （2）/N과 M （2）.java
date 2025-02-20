import java.util.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] noc;
	
	public static void main(String[] args) {
		init();
		permute(1, 0);
		System.out.println(sb);
	}
	
	static void permute(int stx, int dep) {
		if (dep == M) {
			sb.append(Arrays.toString(noc).replaceAll("[\\[\\,\\]]", "")).append('\n');
			return;
		}
		
		for (int i = stx; i <= N; i++) {
			noc[dep] = i;
			permute(i+1, dep+1);
		}
	}
	
	static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt(); noc = new int[M];
		sc.close();
	}
}
