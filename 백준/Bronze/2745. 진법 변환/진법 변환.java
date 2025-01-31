import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		
		String N = in[0].toUpperCase();
		int B = Integer.parseInt(in[1]);
		
		long sum = 0;
		for (int i = 0; i < N.length(); i++) {
			char c = N.charAt(N.length()-1-i);
			
			if ('A' <= c && c <= 'Z') {
				int n = c - 55;
				sum += n * (int)Math.pow(B, i);
			}
			else {
				int n = c - '0';
				sum += n * (int)Math.pow(B, i);
			}
		}
		
		System.out.println(sum);
		
		br.close();
	}
}
