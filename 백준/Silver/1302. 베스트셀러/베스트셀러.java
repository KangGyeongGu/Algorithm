import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String in = br.readLine();

            if(!map.containsKey(in)) {
                map.put(in, 1);
            } else {
                int cnt = map.get(in) + 1;
                map.put(in, cnt);
            }
        }

        int maxC = 0;
        String maxK = "";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            int c = entry.getValue();

            if (c > maxC || (c == maxC && k.compareTo(maxK) < 0)) {
                maxC = c;
                maxK = k;
            }
        }

        System.out.println(maxK);

        br.close();
    }
}