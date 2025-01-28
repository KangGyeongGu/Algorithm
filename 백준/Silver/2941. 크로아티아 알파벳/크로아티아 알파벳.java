import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String[] croatian = { 
            "c=", 
            "c-", 
            "dz=", 
            "d-", 
            "lj", 
            "nj", 
            "s=", 
            "z="
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();

        for (int i = 0; i < croatian.length; i++) {
            if (S.contains(croatian[i])) {
                S = S.replace(croatian[i], "*");
            }
        }
        System.out.println(S.length());

        br.close();
    }
}
