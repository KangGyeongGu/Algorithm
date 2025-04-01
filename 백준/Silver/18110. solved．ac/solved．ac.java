import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[] scores;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		scores = new int[N];
		for (int i = 0; i < N; i++) scores[i] = Integer.parseInt(br.readLine());
		Arrays.sort(scores);
		
		int avg = (int) Math.round(N * 0.15);
		double sum = 0;
		for (int i = avg; i < N - avg; i++) sum += scores[i];
		System.out.println(Math.round(sum / (N-2*avg)));

	}
	
}
