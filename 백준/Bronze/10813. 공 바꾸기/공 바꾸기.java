import java.io.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            a = Integer.parseInt(in[0]); b = Integer.parseInt(in[1]);
            swap(a-1,b-1);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }

        br.close();
    }

    static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}