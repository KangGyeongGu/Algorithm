import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, min;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        init();
        bruteForce();
        System.out.println(min);
        br.close();
    }

    private static void bruteForce() {
        min = Integer.MAX_VALUE;
        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                min = Math.min(min, isChessBoard(r, c));
            }
        }
    }

    private static int isChessBoard(int sr, int sc) {
        int Bcase = 0, Wcase = 0;

        for (int r = sr; r < sr + 8; r++) {
            for (int c = sc; c < sc + 8; c++) {
                boolean expectedB = ((r + c) % 2 == 0);
                
                if (board[r][c] != expectedB) Bcase++;
                if (board[r][c] == expectedB) Wcase++; 
            }
        }

        return Math.min(Bcase, Wcase);
    }

    private static void init() throws IOException {
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        board = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            char[] in = br.readLine().toCharArray();
            for (int m = 0; m < M; m++) {
                board[n][m] = converter(in[m]);
            }
        }
    }

    private static boolean converter(char in) {
        return in == 'B';
    }
}
