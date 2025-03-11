import java.io.*;
import java.util.*;

public class Main {

    static int K, N;
	static long max, ANSWER;
	static int[] cables;
	
	public static void main(String[] args) throws IOException {
		init();
		binarySearch();
		System.out.println(ANSWER);
	}
	
	
	private static void binarySearch() {
		long min = 0;
		while (min < max) {
			
			long mid = (max + min) / 2;
			
			long count = 0;
			for (int i = 0; i < K; i++) {
				count += cables[i] / mid;
			}
			
			if (count < N) {
				max = mid;
			}
			else if (count >= N) {
				min = mid + 1;
			}
		}
		ANSWER = min-1;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		max = 0;
		cables = new int[K];
		for (int i = 0; i < K; i++) {
			cables[i] = Integer.parseInt(br.readLine());
			
			if (max < cables[i]) {
				max = cables[i];
			}
		}
		max++;
	}
}
