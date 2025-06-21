import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] notebook;
    static List<int[][]> stickers = new ArrayList<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];

        for (int k = 0; k < K; k++) {

            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C];
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(sticker);
        }
    }

    private static int[][] rotate(int[][] sticker) {
        int R = sticker.length, C = sticker[0].length;
        int[][] rotated = new int[C][R];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                rotated[c][R - 1 - r] = sticker[r][c];
            }
        }

        return rotated;
    }

    private static boolean attachable(int stx, int sty, int[][] sticker) {
        int R = sticker.length, C = sticker[0].length;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (sticker[r][c] == 1 && notebook[stx + r][sty + c] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attach(int stx, int sty, int[][] sticker) {
        int R = sticker.length, C = sticker[0].length;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (sticker[r][c] == 1) {
                    notebook[stx + r][sty + c] = 1;
                }
            }
        }
    }

    private static void countAttachedStickers() {
        int count = 0;

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (notebook[n][m] == 1) count++;
            }
        }

        System.out.println(count);
    }

    private static void run() {
        for (int[][] sticker: stickers) {
            boolean attached = false;

            for (int rot = 0; rot < 4; rot++) {
                int R = sticker.length, C = sticker[0].length;

                for (int r = 0; r <= N-R; r++) {
                    for (int c = 0; c <= M-C; c++) {
                        if (attachable(r, c, sticker)) {
                            attach(r, c, sticker);
                            attached = true;
                            break;
                        }
                    }
                    if (attached) break;
                }

                if (attached) break;
                sticker = rotate(sticker);
            }
        }

        countAttachedStickers();
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}