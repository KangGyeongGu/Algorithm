import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, TOP;
	static int[] stack = new int[1000000];
	
	
    public static void main(String[] args) throws IOException {
    	
    	N = Integer.parseInt(br.readLine());
    	TOP = -1;
    	
    	for (int i = 0; i < N; i++) {
    		String[] in = br.readLine().split(" ");
    		int inSize = in.length;
    		
    		if (inSize == 1) {
    			switch (in[0]) {
				case "2":
					if (TOP == -1) sb.append(-1).append('\n');
					else sb.append(stack[TOP--]).append('\n');
					break;
				
				case "3":
					sb.append(TOP+1).append('\n');
					break;
				
				case "4":
					if (TOP == -1) sb.append(1).append('\n');
					else sb.append(0).append('\n');
					break;
				
				case "5":
					if (TOP == -1) sb.append(-1).append('\n');
					else sb.append(stack[TOP]).append('\n');
					break;
				}
    		}
    		else {
    			stack[++TOP] = Integer.parseInt(in[1]);
    		}
		}
    	
    	System.out.println(sb);
    }
    
}
