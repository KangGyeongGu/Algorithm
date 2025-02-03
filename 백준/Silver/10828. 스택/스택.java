
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack st = new Stack();
		int N = Integer.parseInt(br.readLine());
		
		while (N-- > 0) {
			String cmd = br.readLine();
			if (cmd.contains("push")) {
				String[] c = cmd.split(" ");
				st.pushX(Integer.parseInt(c[1]));
			} 
			else if (cmd.contains("pop")) {
				st.pop();
			}
			else if (cmd.contains("size")) {
				st.size();
			}
			else if (cmd.contains("empty")) {
				st.empty();
			}
			else if (cmd.contains("top")) {
				st.top();
			}
		}
		
		br.close();
	} 
}

class Stack {
	
	public int top = 0;
	public int[] stack = new int[10000];
	
	public void pushX(int x) {
		stack[top++] = x;
	}
	
	public void pop() {
		if (top == 0) System.out.println("-1");
		else {
			System.out.println(stack[top-1]);
			stack[top--] = 0;
		}
	}
	
	public void size() {
		System.out.println(top);
	}
	
	public void empty() {
		if (top == 0) System.out.println("1");
		else System.out.println("0");
	}
	
	public void top() {
		if (top == 0) System.out.println("-1");
		else System.out.println(stack[top-1]);
	}
	
	
}