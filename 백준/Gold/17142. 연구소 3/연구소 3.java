import java.io.*;
import java.util.*;

public class Main {

    private static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int WALL = 1, INACTIVE_VIRUS = 2;
    static final int[][] DIR = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    static int N, M, map[][], wallCnt, virusCnt, min = Integer.MAX_VALUE;

    static Node[] selected;
    static final List<Node> virusList = new ArrayList<>();

    static boolean[][] visited;
    static Queue<Node> Q;

    private static void init() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			
    			if (map[i][j] == WALL) wallCnt++;
    			else if (map[i][j] == INACTIVE_VIRUS) {
    				virusList.add(new Node(i, j));
    				virusCnt++;
    			}
    		}
    	}
    	selected = new Node[M];
    }
    
    private static void combination(int idx, int selIdx) {
        if (selIdx == M) {
            int dist = bfs(selected, N, map, visited);
            if (dist == -1) return;
            min = Math.min(min, dist);
            return;
        }

        for (int i = idx; i < virusList.size(); i++) {
            selected[selIdx] = virusList.get(i);
            combination(i + 1, selIdx + 1);
        }
    }

    private static int bfs(Node[] start, int N, int[][] map, boolean[][] visited) {
        Q = new ArrayDeque<>(Arrays.asList(start));
        visited = new boolean[N][N];

        for (Node node : start) {
            visited[node.r][node.c] = true;
        }

        int cnt = virusCnt, time = 0;

        while (!Q.isEmpty()) {
            if (cnt == N * N - wallCnt) return time;

            int qSize = Q.size();

            while (qSize-->0) {
                Node node = Q.remove();

                for (int[] dir : DIR) {
                    int nr = node.r + dir[0];
                    int nc = node.c + dir[1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == WALL || visited[nr][nc]) continue;

                    Q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                    if (map[nr][nc] != INACTIVE_VIRUS) cnt++;
                }
            }

            time++;
        }

        return -1;
    }
    
    public static void main(String[] args) throws Exception {
    	init();
        combination(0, 0);
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}