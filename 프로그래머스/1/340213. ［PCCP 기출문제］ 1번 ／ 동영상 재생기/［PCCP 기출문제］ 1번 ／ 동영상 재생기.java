import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int[] t = new int[] { calcSec(video_len), calcSec(pos), calcSec(op_start), calcSec(op_end) };
        
        // opening processing
        if (t[2] <= t[1] && t[1] <= t[3]) t[1] = t[3];
        
        // commands processing
        int curPos = t[1];
        for (int i = 0; i < commands.length; i++) {
			if (commands[i].equals("next")) {
				if (curPos + 10 >= t[0]) curPos = t[0];
				else curPos += 10;
			}
			if (commands[i].equals("prev")) {
				if (curPos - 10 <= 0) curPos = 0;
				else curPos -= 10;
			}
			 if (t[2] <= curPos && curPos <= t[3]) curPos = t[3];
		}
        
        // seconds parsing
        String min = String.valueOf(curPos/60);
        if (curPos/60 < 10) min = "0" + String.valueOf(curPos/60);
       
        String sec = String.valueOf(curPos%60);
        if (curPos%60 < 10) sec = "0" + String.valueOf(curPos%60); 
        
        return min + ":" + sec;
    }
    
    public int calcSec(String S) {
    	StringTokenizer st = new StringTokenizer(S, ":");
    	return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
}