import java.io.*;

public class Main {
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
    	char[] in = br.readLine().toCharArray();
    	int[] alpha = new int[26];
    	
    	for (int i = 0; i < in.length; i++) alpha[in[i]-97]++;
		
    	for (int i = 0; i < alpha.length; i++) sb.append(alpha[i]).append(' ');
		
    	System.out.println(sb);
    	
    	br.close();
    }
}
