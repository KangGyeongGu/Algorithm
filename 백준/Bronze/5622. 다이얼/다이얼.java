import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String[] dial = {
            "0",
            "0",
            "ABC",
            "DEF",
            "GHI",
            "JKL",
            "MNO",
            "PQRS",
            "TUV",
            "WXYZ"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] S = br.readLine().split("");
        int sum = 0;
        for (String s : S) {
            for (int i = 0; i < dial.length; i++) {
                if (dial[i].contains(s)) sum += i+1;
            }
        }
        br.close();
        System.out.println(sum);
    }
}