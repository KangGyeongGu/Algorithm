import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String in = br.readLine();
        
        long hash = 0, r = 1;
        final int MOD = 1234567891;

        for (int i = 0; i < L; i++) {
            hash = (hash + (in.charAt(i) - 'a' + 1) * r) % MOD;
            r = (r * 31) % MOD;
        }

        System.out.println(hash);
    }
}
