import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[][] words = new String[5][15];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine(); 
            for (int j = 0; j < line.length(); j++) {
                words[i][j] = String.valueOf(line.charAt(j)); 
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (words[j][i] == null) continue;
                sb.append(words[j][i]);
            }
        }

        System.out.println(sb);
    }
}
