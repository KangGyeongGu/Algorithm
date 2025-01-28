import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] S = br.readLine().split("");

        Map<String, Integer> cnt = new HashMap<>();

        for (String s : S) {
            String ls = s.toLowerCase();
            cnt.put(ls, cnt.getOrDefault(ls, 0) + 1);
        }

        int max = 0;
        String result = "?";
        boolean isDuplicated = false;

        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
                isDuplicated = false;
            }
            else if (entry.getValue() == max) {
                isDuplicated = true;
            }
        }

        if (isDuplicated) result = "?";

        System.out.println(result.toUpperCase());
    }
}
