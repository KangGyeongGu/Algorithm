import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int[] ans;
	
	public static void main(String[] args) {
		N = sc.nextInt(); sc.close(); ans = new int[N];
		permute(0, "");
		System.out.println(sb);
	}
	
	
	static void permute(int depth, String currentNum) {
		if (depth == N) {
			// logic
			
			sb.append(currentNum).append('\n');
			
			return;
		}
		
		for (int i = (depth > 0 ? 0 : 2); i <= 9; i++) { 
			if (isPrime(Integer.parseInt(currentNum+i))) permute(depth+1, currentNum + i);
		}
	}
	
	
	static boolean isCuriousPrime(String s) {
		for (int i = 1; i < s.length()+1; i++) {
			int x = Integer.parseInt(s.substring(0, i));
			if (!isPrime(x)) return false;
		}
		return true;
	}
	
	static boolean isPrime(int a) {
		if (a == 1) return true;
		
		for (int i = 2; i < a; i++) {
			if (a % i == 0) return false;
		}
		
		return true;
	}
}
