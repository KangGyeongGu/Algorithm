import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int ANS = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < N; i++) {
			if (isDividedNum(i) == N) {
				ANS = i;
				break;
			}
		}
		
		if (ANS == Integer.MIN_VALUE) System.out.println(0);
		else System.out.println(ANS);
		
	}
	
	private static int isDividedNum(int num) {
		
		int sum = 0;
		
		String n = num + "";
		
		for (int i = 0; i < n.length(); i++) {
			sum += n.charAt(i) - '0';
		}
		
		return sum + num;
	
	}
	
}
