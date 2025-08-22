class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            
            int deadline = addMinute(schedules[i], 10);
            int currentDay = startday;
            boolean isLate = false;
            
            for (int j = 0; j < 7; j++) {
                if (currentDay == 6) {
                    currentDay++;
                    continue;
                } else if (currentDay == 7) {
                    currentDay = 1;
                    continue;
                } else {
                    currentDay++;
                }
                
                if (timelogs[i][j] > deadline) {
                    isLate = true;
                    break;
                } 
            }
            
            if (!isLate) answer++;
        }
        
        return answer;
    }
    
    private int addMinute(int time, int add) {
        int hour = time / 100;
        int minute = time % 100;
        
        minute += add;
        
        if (minute >= 60) {
            hour += minute / 60;
            minute = minute % 60;
        }
        
        return hour * 100 + minute;
    }
}