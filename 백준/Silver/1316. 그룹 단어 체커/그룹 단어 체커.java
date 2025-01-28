import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (isGroupString(br.readLine())) cnt++;
        }

        System.out.println(cnt);

        br.close();
    }

    static boolean isGroupString(String S) {
        boolean[] isGroup = new boolean[26];
        char prev = '\0';

        for (int i = 0; i < S.length(); i++) {
            char cur = S.charAt(i);
            if (prev != cur) {
                if (isGroup[cur - 'a']) return false;
                else {
                    isGroup[cur - 'a'] = true;
                    prev = cur;
                }
            }
        }
        return true;
    }
}
