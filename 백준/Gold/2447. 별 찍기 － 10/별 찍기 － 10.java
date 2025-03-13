import java.io.*;

public class Main {
    
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                recursive(i, j, N);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void recursive(int row, int col, int n) {
        if (n == 1) { 
            sb.append("*");
            return;
        }

        int size = n / 3;
        if ((row / size) % 3 == 1 && (col / size) % 3 == 1) {
            sb.append(" ");
        } 
        else {
            recursive(row, col, size);
        }
    }
}