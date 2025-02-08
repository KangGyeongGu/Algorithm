import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] n = br.readLine().split("");
        int[] checker = new int[10];

        for (int i = 0; i < n.length; i++) {
            int x = Integer.parseInt(n[i]);
            if (x == 6) checker[9]++;
            else checker[x]++;
        }

        if (checker[9]%2 == 0) {
            checker[9] /= 2;
        }
        else {
            checker[9] = checker[9]/2 + 1;
        }

        Arrays.sort(checker);
        System.out.println(checker[9]);
        
        br.close();
    }
}