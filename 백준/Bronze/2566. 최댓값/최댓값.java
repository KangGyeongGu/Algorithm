import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = -1;
        int R = 0, C = 0;

        for (int r = 0; r < 9; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 9; c++) {
                int curNum = Integer.parseInt(st.nextToken());
                if (max < curNum) {
                    max = curNum;
                    R = r; C = c;
                }
            }
        }
        System.out.println(max);
        System.out.println(++R + " " + ++C);

        br.close();
    }
}
