import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] sets, candies;
    static List<int[]> groups = new ArrayList<>();

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) n = (n << 1) + (n << 3) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
    
    private static int find(int x) { // 경로압축
        if (sets[x] < 0) return x;
        return sets[x] = find(sets[x]);
    }

    private static void union(int a, int b) { // union by size
        a = find(a);
        b = find(b);
        if (a == b) return;

        if (sets[a] < sets[b]) {
            sets[a] += sets[b];
            candies[a] += candies[b]; // 그룹 별 사탕 개수 누적
            sets[b] = a;
        } else {
            sets[b] += sets[a];
            candies[b] += candies[a];
            sets[a] = b;
        }
    }

    private static void init() throws Exception {
        N = read();
        M = read();
        K = read();

        sets = new int[N + 1];
        candies = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sets[i] = -1;
            candies[i] = read();
        }

        // 1. 입력과 동시에 친구관계 그룹핑
        for (int i = 0; i < M; i++) {
            int u = read(), v = read();
            union(u, v);
        }

        // 2. 그룹별 정보 추출
        for (int i = 1; i <= N; i++) {
            if (i == find(i)) { // i 학생이 속한 그룹 대표번호 (root 번호) 찾기 >> 각 그룹 별 대표 학생만 탐색
                groups.add(new int[]{ -sets[i], candies[i]}); // { 해당 그룹의 인원 수 : 그룹이 가진 사탕 총합 }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        // 0-1 knapsack,
        int[] dp = new int[K]; // 사탕 얻을 수 있는 최대 인원 수
        for (int[] g : groups) { // 그룹별로,
            int size = g[0], sweet = g[1]; // 인원수(weight) - 사탕 개수(val)
            for (int i = K - 1; i >= size; i--) {
                dp[i] = Math.max(dp[i], dp[i - size] + sweet);
            }
        }

        System.out.println(dp[K - 1]); 
    }
}
