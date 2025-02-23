import java.io.*;
import java.util.Stack;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] seq;
    static Stack<Integer> stack = new Stack<>();
    
    public static void main(String[] args) throws IOException {
        init();
        if (stackSequence()) System.out.println(sb);
        else System.out.println("NO");
    }
    
    static boolean stackSequence() {
        int num = 1;
        
        for (int i = 0; i < N; i++) {
            int cur = seq[i];
            
            while (num <= cur) {
                stack.push(num++);
                sb.append("+\n");
            }
            
            if (stack.peek() == cur) {
                stack.pop();
                sb.append("-\n");
            } 
            else return false;
            
        }
        
        return true;
    }
    
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        for (int i = 0; i < N; i++) seq[i] = Integer.parseInt(br.readLine());
    }
}
