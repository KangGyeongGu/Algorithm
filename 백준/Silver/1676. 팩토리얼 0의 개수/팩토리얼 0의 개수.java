import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		int count = 0;
		
		while (N >= 5) {
			count += N / 5;
			N /= 5;
		}
		
		System.out.println(count);
	}
}
