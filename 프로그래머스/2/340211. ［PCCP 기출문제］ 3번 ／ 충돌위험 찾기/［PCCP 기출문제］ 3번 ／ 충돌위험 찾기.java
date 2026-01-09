import java.util.*;

class Solution {
    
    static class Robot {
        List<int[]> path = new ArrayList<>();
        int idx = 0;
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int totalRobots = routes.length;
        
        List<Robot> robots = new ArrayList<>();
        
        for (int i = 0; i < totalRobots; i++) {
            Robot robot = new Robot();
            
            for (int j = 0; j < routes[i].length-1; j++) {
                int[] from = points[routes[i][j]-1];
                int[] to = points[routes[i][j+1]-1];
                buildPath(robot.path, from, to);
            }
            
            int[] start = points[routes[i][0]-1];
            robot.path.add(0, new int[]{start[0], start[1]});
            robots.add(robot);
        }
        
        boolean running = true;
        while (running) {
            running = false;
            Map<String, Integer> countMap = new HashMap<>();
            
            for (Robot robot : robots) {
                if (robot.idx < robot.path.size()) {
                    running = true;
                    int[] pos = robot.path.get(robot.idx);
                    String key = pos[0] + "," + pos[1];
                    countMap.put(key, countMap.getOrDefault(key,0)+1);
                }
            }
            
            for (int count : countMap.values()) {
                if (count >= 2) answer++;
            }
            
            for (Robot robot : robots) {
                robot.idx++;
            }
        }
        
        return answer;
    }
    
    private void buildPath(List<int[]> path, int[] from, int[] to) {
        int r = from[0], c = from[1];
        
        while (r != to[0]) {
            r += (to[0] > r) ? 1 : -1;
            path.add(new int[]{r,c});
        }
        
        while (c != to[1]) {
            c += (to[1] > c) ? 1 : -1;
            path.add(new int[]{r,c});
        }
    }
}