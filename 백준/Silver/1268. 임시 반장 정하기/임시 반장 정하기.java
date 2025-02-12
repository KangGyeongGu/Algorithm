import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][6];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 학생별 같은 반이었던 친구를 저장할 Set 배열
        HashSet<Integer>[] classmates = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            classmates[i] = new HashSet<>();
        }

        // 같은 반이었던 학생들을 HashSet에 저장 (중복 방지)
        for (int grade = 1; grade <= 5; grade++) {
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    if (arr[i][grade] == arr[j][grade]) {
                        classmates[i].add(j);
                        classmates[j].add(i);
                    }
                }
            }
        }

        // 가장 많은 학생과 같은 반이었던 학생 찾기
        int maxCount = 0;
        int leader = 1;

        for (int i = 1; i <= N; i++) {
            int count = classmates[i].size();
            if (count > maxCount) {
                maxCount = count;
                leader = i;
            } else if (count == maxCount && i < leader) {
                leader = i;
            }
        }

        System.out.println(leader);
    }
}
