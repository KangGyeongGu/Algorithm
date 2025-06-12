import java.io.*;
import java.util.*;

public class Main {

    static int N, count;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                count++;
            }

            if (y > 0 && (stack.isEmpty() || stack.peek() < y)) {
                stack.push(y);
            }
        }

        while (!stack.isEmpty()) {
            stack.pop();
            count++;
        }

        System.out.println(count);
    }
}