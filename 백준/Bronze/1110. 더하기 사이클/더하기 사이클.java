import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		n = N;
		int cnt = 0;
		
		while(true) {
			n = calcPlus(n);
			cnt++;
			if (n == N) break;
			
		}
		
		System.out.println(cnt);
		br.close();
		
	} 
	
	static int calcPlus(int N) {
		if (N < 9) {
			int sum = N*10 + N;
			return sum;
		}
		else {
			int sum = N/10 + N%10;
			int tN = N%10*10;
			int oN = sum%10;
			return tN+oN;
		}
	}
}