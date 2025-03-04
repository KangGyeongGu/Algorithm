import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, cnt;
	static List<String> result = new ArrayList<>();
	static Set<String> neverHeard = new HashSet<>();
	static Set<String> neverSeen = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		while (N-->0) {
			neverHeard.add(br.readLine());
		}
		
		while (M-->0) {
			neverSeen.add(br.readLine());
		}
		
		if (neverHeard.size() >= neverSeen.size()) {
			for (String person : neverSeen) {
				if (neverHeard.contains(person)) {
					result.add(person);
				}
			}
		}
		else {
			for (String person : neverHeard) {
				if (neverSeen.contains(person)) {
					result.add(person);
				}
			}
		}
		
		Collections.sort(result);
		System.out.println(result.size());
		for (String p : result) {
			System.out.println(p);
		}
	}
}
