import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<String, Double> scoreTable = new HashMap<>() {{
        put("A+", 4.5);
        put("A0", 4.0);
        put("B+", 3.5);
        put("B0", 3.0);
        put("C+", 2.5);
        put("C0", 2.0);
        put("D+", 1.5);
        put("D0", 1.0);
        put("F", 0.0);
    }};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double scoreSum = 0.0;
        double avg = 0.0;
        for (int i = 0; i < 20; i++) {
            String[] subScore = br.readLine().split(" ");
            double curScore = Double.parseDouble(subScore[1]);
            if (!subScore[2].equals("P")) {
                scoreSum += curScore;
                avg += curScore * scoreTable.get(subScore[2]);
            }
        }
        System.out.println(avg/scoreSum);
    }
}
