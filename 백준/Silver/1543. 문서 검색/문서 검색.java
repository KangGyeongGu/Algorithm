import java.io.*;

public class Main {

	static char[] docs, search;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		docs = br.readLine().toCharArray();
		search = br.readLine().toCharArray(); 
		
		System.out.println(func());
	}
	
	private static int func() {
		
		int l = 0, r = docs.length, window = search.length, ANS = 0;

		while (l+window-1 < r) {
			
			int idx = 0;
			boolean flag = true;

			for (int i = l; i < l + window; i++) {
				if (docs[i] != search[idx++]) {
					flag = false;
					break; 
				}
			}
			if (flag) {
				ANS++;
				l = l + window;
			}
			else l++;
		}

		return ANS;
	}
}
