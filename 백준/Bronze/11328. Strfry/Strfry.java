import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] origin;
	
    public static void main(String[] args) throws IOException {
    	
    	N = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
    		Boolean flag = true;
			
			String in = st.nextToken(); origin = new int[26];
    		for (int j = 0; j < in.length(); j++) {
    			origin[in.charAt(j)-97]++;
    		}
			
    		in = st.nextToken();
    		for (int j = 0; j < in.length(); j++) {
    			origin[in.charAt(j)-97]--;
    		}
			
    		for (int j = 0; j < 26; j++) {
    			if (origin[j] != 0) {
    				flag = false;
    			}
    		}
    		
			if (flag) {
				sb.append("Possible\n");
			} else {
				sb.append("Impossible\n");
			}
		}
    	
    	System.out.println(sb);
    }
}
