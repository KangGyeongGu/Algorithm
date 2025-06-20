import java.io.*;
import java.util.*;

public class Main {

    static class Flower implements Comparable<Flower> {
        int start, end;

        public Flower (int startMonth, int startDay, int endMonth, int endDay) {
            this.start = startMonth * 100 + startDay;
            this.end = endMonth * 100 + endDay;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

    static int N;
    static List<Flower> flowers = new ArrayList<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers.add(new Flower(startMonth, startDay, endMonth, endDay));
        }
    }

    private static void greedy() {
        Collections.sort(flowers);

        int start = 301, end = 1201, idx = 0, count = 0, maxEnd = 0;

        while (start < end) {
            boolean found = false;

            while (idx < N && flowers.get(idx).start <= start) {
                maxEnd = Math.max(maxEnd, flowers.get(idx).end);
                idx++;
                found = true;
            }

            if (!found) {
                System.out.println(0);
                return;
            }

            start = maxEnd;
            count++;
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        init();
        greedy();
    }
}