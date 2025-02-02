import java.io.*;
import java.util.Scanner;

public class Main {
    static final int fYear = 1946;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int cYear = sc.nextInt();
        System.out.println(cYear - fYear);
    }
}