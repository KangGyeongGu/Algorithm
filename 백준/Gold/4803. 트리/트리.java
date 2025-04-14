import java.util.*;

public class Main {

    /** 입력 빠르게 처리 (정수 읽기) */
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 1) + (n << 3) + (c & 15);
        if (c == 13) System.in.read(); // CRLF 처리 (Windows 개행)
        return n;
    }

    /** 1. find(x): 루트 노드 찾기 (경로 압축) */
    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    /** 2. union(u, v): 랭크 기반 병합 (parent에 음수 사용 → 집합 크기) */
    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) {
            isTree[u] = false; // 사이클 발생 → 트리 아님
            return false;
        }

        // 랭크 비교: 더 작은 집합(더 음수)이 부모가 되도록
        if (parent[v] < parent[u]) {
            int temp = u;
            u = v;
            v = temp;
        }

        if (parent[u] == parent[v]) {
            parent[u]--; // 두 집합 크기 같으면 랭크 하나 증가
        }

        parent[v] = u; // v를 u에 붙임
        isTree[u] = isTree[u] & isTree[v]; // 둘 다 트리일 때만 트리 유지

        return true;
    }

    static int N, M, caseNumber = 1;
    static int[] parent;
    static boolean[] isTree;
    static StringBuilder sb = new StringBuilder();

    /** 메인 실행 함수 */
    public static void main(String[] args) throws Exception {

        while ((N = read()) != 0 | (M = read()) != 0) {

            // 0. 초기화
            parent = new int[N + 1];
            isTree = new boolean[N + 1];
            Arrays.fill(parent, -1);
            Arrays.fill(isTree, true);

            // 1. 간선 정보 처리
            for (int i = 0; i < M; i++) {
                int u = read(), v = read();
                union(u, v);
            }

            // 2. 트리 루트 개수 확인
            int treeCount = 0;
            for (int i = 1; i <= N; i++) {
                if (parent[i] < 0 && isTree[i]) {
                    treeCount++;
                }
            }

            // 3. 출력 처리
            sb.append("Case ").append(caseNumber++).append(": ");
            if (treeCount == 0) {
                sb.append("No trees.\n");
            } else if (treeCount == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(treeCount).append(" trees.\n");
            }
        }

        System.out.print(sb);
    }
}
