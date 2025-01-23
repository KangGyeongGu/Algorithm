import java.util.*;

public class Main {
	
	static Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			if (s1.length() == s2.length()) {
				return s1.compareTo(s2); 
			} else {
				return s1.length() - s2.length();
			}
		}
	});
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		
		String in;
		while (N-->0) {
			in = sc.nextLine();
			if (!sortedMap.containsKey(in)) {
				sortedMap.put(in, null);
			}
		}
		
		for (String key : sortedMap.keySet()) System.out.println(key);
		
		sc.close();
	}
}
