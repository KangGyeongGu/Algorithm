import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] arr;
	static ArrayDeque<Integer> seq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		init();
		if (simulate()) System.out.println("Nice");
		else System.out.println("Sad");
	}
	
	static boolean simulate() {
		int idx = 1;
		
		for (int i = 1; i <= N; i++) {
			// main line check
			if (arr[i] != idx) {
				seq.push(arr[i]);
			}
			else {
				idx++;
			}
			
			// sub line check
			while(!seq.isEmpty() && seq.peek() == idx) {
				seq.pop(); idx++;
			}
		}
		
		return seq.isEmpty();
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
	}
}
