import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final char[] OPERATOR = { '+', '-', '*', '/' };
	
	static int T, N, max, min, count;
	static int[] operand, expression;
	static char[] operator, noc;
	static boolean[] iv;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			run();
		}
		System.out.println(sb);
		
	}
	
	static void run() {
		cb(0);
		sb.append(Math.abs(max-min)).append('\n');
	}
	
	/* find all possible operator sequence set */
	static void cb(int depth) {
		if (depth==N-1) {
			int rst = calculate();
			max = Math.max(max, rst); 
			min = Math.min(min, rst);
			return;
		}
		
		for (int i = 0; i < operator.length; i++) {
			if (iv[i]) continue;
			if (i > 0 && operator[i] == operator[i-1] && !iv[i-1]) continue; // deduplication, 
			iv[i] = true;
			noc[depth] = operator[i];
			cb(depth+1);
			iv[i] = false;
		}
	}

	/* calculate each expression */
	static int calculate() {
		int cur = operand[0];
		for (int i = 1; i < operand.length; i++) {
			int right = operand[i]; 
			cur = operate(cur, noc[i-1], right); 
		}
		return cur;
	}
	
	static int operate(int left, int op, int right) {
		if (op == '+') return left + right;
		if (op == '-') return left - right;
		if (op == '*') return left * right;
		else {
			return (int) (left / right);
		}
	}
	
	/* Test case initailizer */
	static void init(int tc) throws IOException {
		// test case initialize
		sb.append("#").append(tc).append(" ");
		N = Integer.parseInt(br.readLine());
		
		// operator initialize
		operator = new char[N-1]; 
		int idx = 0; 
		
		noc = new char[N-1]; 
		iv = new boolean[N-1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			
			int num = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < num; j++) {
				operator[idx++] = OPERATOR[i];
			}
		}
		
		// operand initialize
		operand = new int[N]; expression = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < operand.length; i++) operand[i] = Integer.parseInt(st.nextToken()); 
		
		// output initialize
		max = Integer.MIN_VALUE; min = Integer.MAX_VALUE;
	}
}
