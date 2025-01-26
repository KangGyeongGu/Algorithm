import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        int[] arr = new int[N];
        int i,j,k;

        for (int idx = 0; idx < M; idx++) {
            String[] ijk = br.readLine().split(" ");
            i = Integer.parseInt(ijk[0]);
            j = Integer.parseInt(ijk[1]);
            k = Integer.parseInt(ijk[2]);

            for (int l = i-1; l < j; l++) {
                arr[l] = k;
            }
        }

        for (int l = 0; l < N; l++) {
            System.out.print(arr[l] + " ");
        }
        
        br.close();;
    }
}