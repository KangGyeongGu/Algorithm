class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        double t1 = h1 * 3600.0 + m1 * 60.0 + s1;
        double t2 = h2 * 3600.0 + m2 * 60.0 + s2;
        
        double T_sm = 3600.0 / 59.0;
        double T_sh = 43200.0 / 719.0;
        double T_all = 43200.0;
        
        long countSM = countEvents(t1, t2, T_sm);
        long countSH = countEvents(t1, t2, T_sh);
        long countALL = countEvents(t1, t2, T_all);
        
        return (int)(countSM + countSH - countALL);
    }
    
    private long countEvents(double t1, double t2, double period) {
        double eps = 1e-9;
        long start = (long) Math.ceil((t1-eps) / period);
        long end = (long) Math.floor((t2+eps) / period);
        return Math.max(0, end - start + 1);
    }
}

