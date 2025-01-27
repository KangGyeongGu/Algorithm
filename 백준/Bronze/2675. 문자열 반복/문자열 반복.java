import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-->0) {
            
            String[] tCase = br.readLine().split(" ");
            int R = Integer.parseInt(tCase[0]);
            String S = tCase[1];

            for (int i = 0; i < S.length(); i++) {
                for (int j = 0; j < R; j++) {
                    System.out.print(S.charAt(i));
                }
            }
            System.out.println();
        }
    }
}