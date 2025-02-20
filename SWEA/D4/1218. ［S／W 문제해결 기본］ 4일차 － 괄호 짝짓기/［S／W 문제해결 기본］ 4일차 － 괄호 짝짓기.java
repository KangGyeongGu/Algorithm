import java.io.*;
import java.util.Stack;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static String in;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		
		for (int tc = 1; tc <= 10; tc++) {
			init(tc);
			isBalanced();
		}
		System.out.println(sb);
		br.close();
	}
	
	static void isBalanced() {
		stack = new Stack<>();
		
		for (int i = 0; i < in.length(); i++) {
			char ch = in.charAt(i);
			if (isOpeningBraket(ch)) stack.push(ch);
			else if(!isValidCloseBraket(ch)) break;
		}
		
		if (stack.isEmpty()) sb.append(1).append("\n");
		else sb.append(0).append("\n");
	}
	
	static boolean isOpeningBraket(char ch) {
		return ch == '(' || ch == '[' || ch == '{' || ch == '<';
	}
	
	static boolean isValidCloseBraket(char ch) {
		if (ch == ')') {
			if (stack.isEmpty() || !stack.pop().equals('(')) return false;
		}
		else if (ch == ']') {
			if (stack.isEmpty() || !stack.pop().equals('[')) return false;
		}
		else if (ch == '}') {
			if (stack.isEmpty() || !stack.pop().equals('{')) return false;
		}
		else if (ch == '>') {
			if (stack.isEmpty() || !stack.pop().equals('<')) return false;
		}
		return true;
	}
	
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());
		in = br.readLine();
	}
}
