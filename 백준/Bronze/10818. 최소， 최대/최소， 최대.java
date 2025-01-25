import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        String[] arr = br.readLine().split(" ");
        int MAX = Integer.parseInt(arr[0]);
        int MIN = Integer.parseInt(arr[0]);
        int x ;

        for (String a : arr) {
            x = Integer.parseInt(a);
            if (x > MAX) MAX = x;
            else if (x < MIN) MIN = x;
        }

        System.out.println(MIN + " " + MAX);
    }
}