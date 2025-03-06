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
    
    static int N, M;
    static List<Node> chickens = new ArrayList<>();
    static List<Node> houses = new ArrayList<>();
    static boolean[] selected;
    static int[][] distances;
    static int minChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();  
        selectChickens(0, 0);
        System.out.println(minChickenDistance);
    }

    private static void selectChickens(int start, int depth) {
        if (depth == M) { 
            minChickenDistance = Math.min(minChickenDistance, calcCityChickenDistance());
            return;
        }
        
        for (int i = start; i < chickens.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                selectChickens(i + 1, depth + 1);
                selected[i] = false;
            }
        }
    }

    private static void precomputeDistances() {
        int hSize = houses.size();
        int cSize = chickens.size();
        distances = new int[hSize][cSize];

        for (int i = 0; i < hSize; i++) {
            for (int j = 0; j < cSize; j++) {
                distances[i][j] = Math.abs(houses.get(i).x - chickens.get(j).x) +
                                  Math.abs(houses.get(i).y - chickens.get(j).y);
            }
        }
    }

    private static int calcCityChickenDistance() {
        int totalDistance = 0;

        for (int i = 0; i < houses.size(); i++) {
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < chickens.size(); j++) {
                if (selected[j]) {
                    minDist = Math.min(minDist, distances[i][j]);
                }
            }
            totalDistance += minDist;
        }
        return totalDistance;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) houses.add(new Node(i, j));
                else if (value == 2) chickens.add(new Node(i, j));
            }
        }

        selected = new boolean[chickens.size()];
        precomputeDistances();
    }
}
