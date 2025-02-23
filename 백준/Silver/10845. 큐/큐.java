import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, l, r;
	static int[] stack = new int[10001];
	
    public static void main(String[] args) throws IOException {
    	N = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cmdExcute(st.nextToken());
		}
    	
    	System.out.println(sb);
    	br.close();
    }
    
    static void cmdExcute(String cmd) {
    	switch (cmd) {
		case "push":
			int elem  = Integer.parseInt(st.nextToken());
			stack[r++] = elem;
			break;
		
		case "pop":
			if (l==r) sb.append("-1\n");
			else sb.append(stack[l++]).append('\n');
			break;
		
		case "size":
			sb.append(r-l).append('\n');
			break;
		
		case "empty":
			if (l==r) sb.append("1\n");
			else sb.append("0\n");
			break;
		
		case "front":
			if (l==r) sb.append("-1\n");
			else sb.append(stack[l]).append('\n');
			break;
			
		case "back":
			if (l==r) sb.append("-1\n");
			else sb.append(stack[r-1]).append('\n');
			break;
		}
    }
}
