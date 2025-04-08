import java.io.*;
import java.util.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws Exception {
        int N = read();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) A[i] = read();

        ArrayList<Integer> dp = new ArrayList<>();

        for (int num : A) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0) idx = -idx - 1;

            if (idx == dp.size()) {
                dp.add(num);
            } else {
                dp.set(idx, num);
            }
        }

        bw.write(dp.size() + "\n");
        bw.flush();
        bw.close();
    }
}
