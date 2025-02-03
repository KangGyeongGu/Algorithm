import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-->0) {
			Stack<Character> vps = new Stack<>();
			String tC = br.readLine();
			for (int i = 0; i < tC.length(); i++) {
				if (vps.isEmpty()) vps.add(tC.charAt(i));
				else if (tC.charAt(i) == '(') vps.add(tC.charAt(i));
				else if (vps.peek() == '(' && tC.charAt(i) == ')') vps.pop();
			
			}
			if (vps.isEmpty()) System.out.println("YES");
			else System.out.println("NO");
		}
	} 
}
