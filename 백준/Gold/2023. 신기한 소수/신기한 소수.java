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
		if (depth == N) { // check basis,
			sb.append(currentNum).append('\n');
			return;
		}
		
		for (int i = (depth > 0 ? 0 : 2); i <= 9; i++) { // recursive call only if length N (1,2,3,4,..) number is prime,
			if (isPrime(Integer.parseInt(currentNum+i))) permute(depth+1, currentNum + i);
		}
	}
	
	static boolean isPrime(int a) { // check if the number is prime,
		if (a == 1) return true;
		for (int i = 2; i < a; i++) if (a % i == 0) return false;
		return true;
	}
}
