import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String[] W = new String[N];
        for (int i = 0; i < N; i++) {
            W[i] = br.readLine();
        }

        Arrays.sort(W, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });
        
        sb.append(W[0]).append('\n');
        for (int i = 1; i < N; i++) {
            if (!W[i].equals(W[i-1])) sb.append(W[i]).append('\n');
        }
        System.out.println(sb);

        br.close();
    }
}