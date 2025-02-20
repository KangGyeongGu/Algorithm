import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		
		while (true) {
			String in = br.readLine();
			
			if (in.equals(".")) break;
			
			if (isBalanced(in)) sb.append("yes").append('\n');
			else sb.append("no").append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean isBalanced(String in) {
		stack = new Stack<>();
		
		for (int i = 0; i < in.length(); i++) {
			char ch = in.charAt(i);
			
			if (ch == '(' || ch == '[') {
				stack.push(ch);
			}
			
			else if (ch == ')') {
				if (stack.isEmpty() || !stack.pop().equals('(')) return false;
			}
			
			else if (ch == ']') {
				if (stack.isEmpty() || !stack.pop().equals('[')) return false;
			}
		}
		
		return stack.isEmpty();
	}
	
}
