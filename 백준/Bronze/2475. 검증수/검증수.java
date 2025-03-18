import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;

		int size = st.countTokens();
		for (int i = 0; i < size; i++) {
			int in = Integer.parseInt(st.nextToken());
			sum += in*in;
		}
		
		System.out.println(sum % 10);
	}
}
