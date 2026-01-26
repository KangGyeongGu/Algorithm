import java.util.*;

class Solution {
    
    static class Expression {
        String A, B, C;
        char op;
        boolean unknown;
        String rawA, rawB;
        
        Expression(String A, char op, String B, String C) {
            this.A = A;
            this.op = op;
            this.B = B;
            this.C = C;
            this.unknown = C.equals("X");
            this.rawA = A;
            this.rawB = B;
        }
        
        String formatWith(String newC) {
            return rawA + " " + op + " " + rawB + " = " + newC;
        }
    }
    
    public String[] solution(String[] expressions) {
        List<Expression> list = new ArrayList<>();
        List<Expression> unknowns = new ArrayList<>();
        
        for (String s : expressions) {
            String[] t = s.split(" ");
            String A = t[0];
            char op = t[1].charAt(0);
            String B = t[2];
            String C = t[4];
            
            Expression e = new Expression(A, op, B, C);
            list.add(e);
            if (e.unknown) unknowns.add(e);
        }
        
        List<Integer> bases = new ArrayList<>();
        for (int base = 2; base <= 9; base++) {
            if (isValidBase(base, list)) bases.add(base);
        }
        
        String[] answers = new String[unknowns.size()];
        for (int i = 0; i < unknowns.size(); i++) {
            Expression e = unknowns.get(i);
            
            String candidate = null;
            boolean same = true;
            
            for (int base : bases) {
                int a = parseBase(e.A, base);
                int b = parseBase(e.B, base);
                int val = (e.op == '+') ? (a + b) : (a - b);
                
                String res = toBase(val, base);
                
                if (candidate == null) candidate = res;
                else if (!candidate.equals(res)) {
                    same = false;
                    break;
                }
            }
            
            answers[i] = e.formatWith(same ? candidate : "?");
        }
        
        return answers;
    }
    
    
    private boolean isValidBase(int base, List<Expression> expressions) {
        for (Expression e : expressions) {
            if (!digitsOk(e.A, base) || !digitsOk(e.B, base)) return false;
            
            int a = parseBase(e.A, base);
            int b = parseBase(e.B, base);
            int expected = (e.op == '+') ? (a + b) : (a - b);
            
            if (!e.unknown) {
                if (!digitsOk(e.C, base)) return false;
                
                int c = parseBase(e.C, base);
                if (c != expected) return false;
            }
        }
        
        return true;
    }
    
    private boolean digitsOk(String s, int base) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') return false;
            
            int d = ch - '0';
            if (d >= base) return false;
        }
        return true;
    }
    
    private int parseBase(String s, int base) {
        int v = 0;
        for (int i = 0; i < s.length(); i++) {
            v = v * base + (s.charAt(i) - '0');
        }
        return v;
    }
    
    private String toBase(int value, int base) {
        if (value == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        int v = value;
        while (v > 0) {
            sb.append((char) ('0' + (v % base)));
            v /= base;
        }
        return sb.reverse().toString();
    }
}