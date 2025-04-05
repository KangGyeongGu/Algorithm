import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {

        int M = read(), N = read();
        boolean[] isNotPrime = new boolean[N + 1];

        isNotPrime[0] = isNotPrime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = M; i <= N; i++) {
            if (!isNotPrime[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush(); bw.close();
    }
}
