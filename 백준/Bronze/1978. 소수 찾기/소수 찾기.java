import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());  
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (isPrime(arr[i])) count++;
		}
		
		System.out.println(count);
	}
	
	private static boolean isPrime(int n) {
		
		if (n==1) return false;
		
		if (n==2) return true;
		
		for (int i = 2; i < n; i++) {
			if (n%i == 0) return false;
			
		}
		
		return true;
	}
}
