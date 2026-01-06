import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        String N = n + "";
        int len = N.length();
        
        for (int i = 0; i < len; i++) {
            answer += N.charAt(i) - '0';
        }

        return answer;
    }
}