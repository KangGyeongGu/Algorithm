import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(factorial(N)/(factorial(K)*factorial(N-K)));
	}
	
	private static int factorial(int n) {
		int sum = 1;
		for (int i = 1; i <= n; i++) sum *= i;
		return sum;
	}
}