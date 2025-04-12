import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, K, n;
	static String op;
	static TreeMap<Integer, Integer> map = new TreeMap<>();
	
	private static void readCmd() throws Exception {
		st = new StringTokenizer(br.readLine());
		op = st.nextToken();
		n = Integer.parseInt(st.nextToken());
	}
	
	public static void main(String[] args) throws Exception {
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			map.clear();
			
			K = Integer.parseInt(br.readLine());
			
			while (K-->0) {
				
 				readCmd();
 				
 				switch (op) {
	 				case "I" :
	 					map.put(n, map.getOrDefault(n, 0) + 1);
	 					break;
	 					
	 				case "D" :
	 					if (map.isEmpty()) break;
	 					
	 					int key = (n==1) ? map.lastKey() : map.firstKey();
	 					
	 					if (map.get(key) == 1) map.remove(key);
	 					else map.put(key, map.get(key)-1);
	 					
	 					break;
 				}
 				
			}
			if (map.isEmpty()) sb.append("EMPTY\n");
			else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
		}
		
		System.out.println(sb);
	}
}