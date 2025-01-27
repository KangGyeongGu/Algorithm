import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            String[] S = br.readLine().split("");
            sb.append(S[0]).append(S[S.length-1]).append("\n");

        }
        System.out.println(sb);
    }
}