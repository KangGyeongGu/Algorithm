import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] trees;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(trees);
		
		System.out.println(cut());
		
	}
	
	private static int cut() {
		
		int low = 0;
		int max = trees[N-1];
		int result = 0;
		
		while (low <= max) {
			
			int mid = (low + max) / 2;
			long sum = 0;
			
			for (int tree : trees) if (tree > mid) sum += (tree - mid);
			
			if (sum >= M) {
				result = mid;
				low = mid + 1;
			}
			else {
				max = mid - 1;
			}
			
		}
		
		return result;
	}
}