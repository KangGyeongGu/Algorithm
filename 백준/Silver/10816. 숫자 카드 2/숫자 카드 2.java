import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static Map<Integer, Integer> cards = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(st.nextToken());
			
			cards.put(c, cards.getOrDefault(c, 0) + 1);
		}
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int c = Integer.parseInt(st.nextToken());
			
			sb.append(cards.getOrDefault(c, 0) + " ");
		}
		
		System.out.println(sb);
	}
}