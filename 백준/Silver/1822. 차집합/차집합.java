import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<Integer> A = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            A.remove(target);
        }

        int count = A.size();
        System.out.println(count);

        if (count != 0) {
            List<Integer> xorList = new ArrayList<>(A);
            Collections.sort(xorList);
            for (int elem : xorList) System.out.print(elem + " ");
        }
    }
}
