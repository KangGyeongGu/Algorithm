import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] origin, fry;
	
    public static void main(String[] args) throws IOException {
    	
    	N = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
    		Boolean flag = true;
			
			String in = st.nextToken(); origin = new int[26];
    		for (int j = 0; j < in.length(); j++) origin[in.charAt(j)-97]++;
			
    		in = st.nextToken(); fry = new int[26];
    		for (int j = 0; j < in.length(); j++) fry[in.charAt(j)-97]++;
			
    		for (int j = 0; j < 26; j++) if (origin[j] != fry[j]) flag = false;
    		
			if (flag) sb.append("Possible\n");
			else sb.append("Impossible\n");
		}
    	
    	System.out.println(sb);
    }
}
