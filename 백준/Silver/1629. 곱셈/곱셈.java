import java.io.*;
import java.util.*;

public class Main {
	
	static long A, B, C;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(recursive(A, B, C));
		
	}
	
	private static long recursive(long a, long b, long c) {
		if (b == 1) return a % c;
		
		long num = recursive(a, b/2, c);
		num = num * num % c;
		
		if (b % 2 == 0) return num;
		
		return num * a % c;
	}
}