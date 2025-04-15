import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Cell implements Comparable<Cell> {
        int r, c, x, time;
        boolean activated;

        Cell(int r, int c, int x) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.time = x;
            this.activated = false;
        }

        @Override
        public int compareTo(Cell o) {
            return o.x - this.x;
        }

        public int key() {
            return r * OFFSET + c;
        }
    }

    static final int[][] DIR = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static final int OFFSET = 2000;

    static int T, N, M, K;
    static Map<Integer, Cell> cellMap = new HashMap<>();
    static PriorityQueue<Cell> pQ = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cellMap.clear();
            pQ.clear();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x > 0) {
                        int r = i + K;
                        int c = j + K;
                        Cell cell = new Cell(r, c, x);
                        cellMap.put(cell.key(), cell);
                        pQ.offer(cell);
                    }
                }
            }

            for (int time = 1; time <= K; time++) {
                PriorityQueue<Cell> nextQueue = new PriorityQueue<>();
                List<Cell> newCells = new ArrayList<>();

                while (!pQ.isEmpty()) {
                    Cell cell = pQ.poll();

                    if (!cell.activated) {
                        cell.time--;
                        if (cell.time == 0) {
                            cell.activated = true;
                            cell.time = cell.x;
                            nextQueue.offer(cell);
                        } else {
                            nextQueue.offer(cell);
                        }
                    } else {
                        cell.time--;
                        if (cell.time == cell.x - 1) {
                            for (int[] dir : DIR) {
                                int nr = cell.r + dir[0];
                                int nc = cell.c + dir[1];
                                int key = nr * OFFSET + nc;

                                if (!cellMap.containsKey(key)) {
                                    Cell newCell = new Cell(nr, nc, cell.x);
                                    cellMap.put(key, newCell);
                                    newCells.add(newCell);
                                }
                            }
                        }
                        if (cell.time > 0) nextQueue.offer(cell);
                    }
                }

                for (Cell newCell : newCells) {
                    if (cellMap.get(newCell.key()) == newCell) {
                        nextQueue.offer(newCell);
                    }
                }

                pQ = nextQueue;
            }

            sb.append("#").append(t).append(" ").append(pQ.size()).append("\n");
        }

        System.out.print(sb);
    }
}
