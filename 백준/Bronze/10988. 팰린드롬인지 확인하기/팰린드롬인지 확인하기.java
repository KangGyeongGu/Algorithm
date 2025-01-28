import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] S = br.readLine().split("");

        System.out.println(isPalindorme(S));
    }

    static int isPalindorme(String [] S) {
        int s = 0, e = S.length-1;

        while (s < e) {
            if (!S[s].equals(S[e])) return 0;
            s++; e--;
        }
        return 1;
    }
}
