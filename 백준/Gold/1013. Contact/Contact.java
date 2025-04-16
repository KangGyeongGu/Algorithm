import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
    	int T = Integer.parseInt(br.readLine());
    	
    	while (T-->0) {
    		String in = br.readLine();
    		
    		if (in.matches("(100+1+|01)+")) sb.append("YES\n");
    		else sb.append("NO\n");
    	}
    	
    	System.out.println(sb);
	}
}
