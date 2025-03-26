import java.io.*;

public class Main {
	
	static String N;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = "";
        
        while (!(N = br.readLine()).equals("0")) {
        	
        	int i = 0;
        	int j = N.length()-1;
        	boolean flag = true;
        	
        	while (i<j) {
        		
        		if (N.charAt(i) != N.charAt(j)) {
        			flag = false;
        			break;
        		}
        		i++; j--;
        	}
        	
        	if (!flag) sb.append("no" + '\n');
        	else sb.append("yes" + '\n');
        	
        }
        
        System.out.println(sb);
    }
}
