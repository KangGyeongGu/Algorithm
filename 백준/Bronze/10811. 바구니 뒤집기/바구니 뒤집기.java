import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        arr = IntStream.range(1,N+1).toArray();

        for (int m = 0; m < M; m++) {
            int i = sc.nextInt();
            int j = sc.nextInt();

            if (i < j) swap(i-1, j-1);
            else if (i > j) swap(j-1, i-1);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void swap(int s, int d) {
        while (s < d) {
            int temp = arr[s];
            arr[s] = arr[d];
            arr[d] = temp;
            s++; d--;
        }
    }
}