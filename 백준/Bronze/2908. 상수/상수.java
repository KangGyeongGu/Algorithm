import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        String[] N = NM[0].split("");
        String[] M = NM[1].split("");

        int n = Integer.parseInt(reverse(N));
        int m = Integer.parseInt(reverse(M));

        System.out.println(Math.max(n,m));
        
        br.close();
    }

    public static String reverse(String[] num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num[2]).append(num[1]).append(num[0]);
        return sb.toString();
    }
}