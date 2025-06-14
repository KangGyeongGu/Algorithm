import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static Map<String, Integer> dictByName = new HashMap<>();
    static Map<Integer, String> dictById = new HashMap<>();

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            dictByName.put(name, i);
            dictById.put(i, name);
        }

        for (int i = 0; i < M; i++) {
            String in = br.readLine();

            if (isNumeric(in)) {
                int num = Integer.parseInt(in);
                sb.append(dictById.get(num)).append('\n');
            } else {
                sb.append(dictByName.get(in)).append('\n');
            }
        }

        System.out.println(sb);
    }
}