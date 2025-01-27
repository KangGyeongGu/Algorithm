import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] sBool = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();

        for (int i = 0; i < S.length(); i++) {
            sBool[S.charAt(i)-97] = true;
        }

        for (int i = 0; i < 26; i++) {
            if (!sBool[i]) System.out.print("-1 ");
            else System.out.print(S.indexOf(i+97) + " ");
        }
    }
}