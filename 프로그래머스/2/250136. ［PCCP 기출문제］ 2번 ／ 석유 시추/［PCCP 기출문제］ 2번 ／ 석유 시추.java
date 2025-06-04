import java.util.*;

class Solution {
    
    static class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private int N, M;
    private boolean[][] visited;
    private final int[][] DIRECTIONS = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    private Map<Integer, Integer> oilRegionAmount = new HashMap<>();
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        
        markOilRegions(land);
        
        return findMaxOilFromPipe(land);
    }
    
    /**
     * Phase 1: Use BFS to identify connected oil regions and mark them with unique IDs
     */
    private void markOilRegions(int[][] land) {
        int regionId = 2; // Start from 2 to avoid confusion with 0 and 1
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (land[row][col] == 1 && !visited[row][col]) {
                    int oilAmount = bfsMarkRegion(land, new Coord(row, col), regionId);
                    oilRegionAmount.put(regionId, oilAmount);
                    regionId++;
                }
            }
        }
    }
    
    /**
     * BFS to mark a connected oil region and count total oil amount
     */
    private int bfsMarkRegion(int[][] land, Coord start, int regionId) {
        Queue<Coord> queue = new ArrayDeque<>();
        queue.offer(start);
        
        land[start.x][start.y] = regionId;
        visited[start.x][start.y] = true;
        
        int totalOil = 1;
        
        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            
            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];
                
                if (isValidOilCell(newX, newY, land)) {
                    visited[newX][newY] = true;
                    land[newX][newY] = regionId;
                    totalOil++;
                    queue.offer(new Coord(newX, newY));
                }
            }
        }
        
        return totalOil;
    }
    
    /**
     * Check if coordinates are valid and contain unvisited oil
     */
    private boolean isValidOilCell(int x, int y, int[][] land) {
        return x >= 0 && x < N && y >= 0 && y < M && 
               !visited[x][y] && land[x][y] == 1;
    }
    
    /**
     * Phase 2: For each column, calculate total oil that can be extracted
     */
    private int findMaxOilFromPipe(int[][] land) {
        int maxOil = 0;
        
        for (int col = 0; col < M; col++) {
            Set<Integer> encounteredRegions = new HashSet<>();
            int columnOilTotal = 0;
            
            for (int row = 0; row < N; row++) {
                int regionId = land[row][col];
                
                // If we hit a new oil region, add its total amount
                if (regionId > 1 && !encounteredRegions.contains(regionId)) {
                    columnOilTotal += oilRegionAmount.get(regionId);
                    encounteredRegions.add(regionId);
                }
            }
            
            maxOil = Math.max(maxOil, columnOilTotal);
        }
        
        return maxOil;
    }
}