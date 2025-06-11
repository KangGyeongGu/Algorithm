import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<String> universeSignatures = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] planets = new int[N];

            for (int j = 0; j < N; j++) {
                planets[j] = Integer.parseInt(st.nextToken());
            }

            String signature = compressCoordinates(planets);
            universeSignatures.add(signature);
        }

        Map<String, Integer> count = new HashMap<>();
        for (String signature : universeSignatures) {
            count.put(signature, count.getOrDefault(signature, 0) + 1);
        }

        int result = 0;
        for (int cnt : count.values()) {
            result += cnt * (cnt - 1) / 2;
        }

        System.out.println(result);
    }

    private static String compressCoordinates(int[] planets) {
        int n = planets.length;

        Set<Integer> uniqueSet = new TreeSet<>();
        for (int planet : planets) {
            uniqueSet.add(planet);
        }

        List<Integer> sortedUnique = new ArrayList<>(uniqueSet);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(",");

            int rank = Collections.binarySearch(sortedUnique, planets[i]);
            sb.append(rank);
        }

        return sb.toString();
    }
}