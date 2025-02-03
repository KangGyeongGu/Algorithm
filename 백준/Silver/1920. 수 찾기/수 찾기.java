import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		Set<Integer> nS = new HashSet<>(N);
		while (N-->0) {
			nS.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		while (M-->0) {
			int num = Integer.parseInt(st.nextToken());
			if (nS.contains(num)) sb.append("1").append("\n");
			else sb.append("0").append("\n");
		}
		
		System.out.println(sb);
		
	} 
}