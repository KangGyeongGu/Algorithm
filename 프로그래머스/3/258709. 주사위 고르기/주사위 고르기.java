import java.util.*;

class Solution {
    int n;
    int[][] dice;
    int maxWins = 0;
    int[] bestCombination;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        this.bestCombination = new int[n/2];
        
        int[] selected = new int[n/2];
        combination(0, 0, selected);
        
        int[] answer = new int[n/2];
        for (int i = 0; i <n/2; i++) {
            answer[i] = bestCombination[i] + 1;
        }
        
        return answer;
    }
    
    private void combination(int start, int depth, int[] selected) {
        if (depth == n/2) {
            calcWinRate(selected);
            return;
        }
        
        for (int i = start; i < n; i++) {
            selected[depth] = i;
            combination(i+1, depth+1, selected);
        }
    }
    
    private void calcWinRate(int[] aSelected) {
        boolean[] isSelected = new boolean[n];
        for (int idx : aSelected) {
            isSelected[idx] = true;
        }
        
        int[] bSelected = new int[n/2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!isSelected[i]) {
                bSelected[idx++] = i;
            }
        }
        
        List<Integer> aSums = getAllSums(aSelected);
        List<Integer> bSums = getAllSums(bSelected);
        
        Collections.sort(aSums);
        Collections.sort(bSums);
        
        int wins = 0;
        for (int aSum : aSums) {
            int left = 0, right = bSums.size();
            while (left < right) {
                int mid = (left+right) /2;
                if (bSums.get(mid) < aSum) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            wins += left;
        }
        
        if (wins > maxWins) {
            maxWins = wins;
            bestCombination = aSelected.clone();
        }
    }
    
    private List<Integer> getAllSums(int[] selected) {
        List<Integer> sums = new ArrayList<>();
        generateSums(selected, 0, 0, sums);
        return sums;
    }
    
    private void generateSums(int[] selected, int diceIdx, int currentSum, List<Integer> sums) {
        if (diceIdx == selected.length) {
            sums.add(currentSum);
            return;
        }
        
        int diceNum = selected[diceIdx];
        for (int face : dice[diceNum]) {
            generateSums(selected, diceIdx+1, currentSum + face, sums);
        }
    }
}