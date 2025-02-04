import java.io.*;
import java.util.*;

public class Main {
	
	static int[] check = new int[10];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int R = Integer.parseInt(br.readLine())*Integer.parseInt(br.readLine())*Integer.parseInt(br.readLine());

		int next = R, idx = 0;
		while (true) {
			idx = next%10; 
			check[idx]++;
			next /= 10;
			if (next < 10) {
				check[next]++;
				break;
			}
		}
		
		for (int i = 0; i < check.length; i++) {
			System.out.println(check[i]);
		}
	}
}