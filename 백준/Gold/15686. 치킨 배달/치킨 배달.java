import java.util.*;
import java.io.*;

public class Main {
    
    static class Node {
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M;
    static int[][] map;
    static boolean[] isChikens;
    static List<Node> chickens = new ArrayList<>();
    static List<Node> houses = new ArrayList<>();
    static List<Node> chickenCombi = new ArrayList<>();
    
    static int minChickenDistance = Integer.MAX_VALUE; 

    public static void main(String[] args) throws IOException {
        init(); 
        run(0, 0); 
        System.out.println(minChickenDistance);
    }

    private static void run(int start, int depth) {
        if (depth == M) { 
            minChickenDistance = Math.min(minChickenDistance, calcCityChickenDistance());
            return;
        }
        
        for (int i = start; i < chickens.size(); i++) {
            if (isChikens[i]) continue;

            isChikens[i] = true;
            chickenCombi.add(chickens.get(i));
            run(i + 1, depth + 1);
            chickenCombi.remove(chickenCombi.size() - 1); 
            isChikens[i] = false;
        }
    }

    private static int calcCityChickenDistance() {
        int totalDistance = 0;

        for (Node house : houses) {
            int minDistance = Integer.MAX_VALUE;

            for (Node chicken : chickenCombi) {
                int distance = calcDistance(house.x, house.y, chicken.x, chicken.y);
                minDistance = Math.min(minDistance, distance);
            }

            totalDistance += minDistance;
        }

        return totalDistance;
    }

    private static int calcDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int in = Integer.parseInt(st.nextToken());
                map[i][j] = in;

                if (in == 1) houses.add(new Node(i, j)); 
                if (in == 2) chickens.add(new Node(i, j)); 
            }
        }

        isChikens = new boolean[chickens.size()];
    }
}