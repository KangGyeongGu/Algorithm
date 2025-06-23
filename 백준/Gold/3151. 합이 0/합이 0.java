import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] skill;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        skill = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            skill[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void twoPointer() {
        // 투포인터를 위한 정렬
        Arrays.sort(skill);

        // 가능한 조합의 개수
        long count = 0;

        for (int i = 0; i < N-2; i++) {
            int fixed = skill[i]; // 첫번째 고정 값
            int left = i+1, right = N-1; // 고정 값 기준으로 투포인터 탐색

            while (left < right) {
                int sum = fixed + skill[left] + skill[right]; // 세 학생의 실력의 합

                if (sum == 0) {
                    if (skill[left] == skill[right]) {
                        int n = right - left + 1;
                        count += (long)n * (n-1)/2;
                        break;
                    } else {
                        int lCount = 1, rCount = 1;

                        while (left+1 < right && skill[left] == skill[left+1]) {
                            lCount++;
                            left++;
                        }

                        while (right-1 > left && skill[right] == skill[right-1]) {
                            rCount++;
                            right--;
                        }

                        count += (long)lCount * rCount;
                        left++;
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        init();
        twoPointer();
    }
}