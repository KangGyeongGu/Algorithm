import javax.swing.text.html.ListView;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        boolean[] shortcuts = new boolean[26];
        for (int n = 0; n < N; n++) {

            String[] cmd = br.readLine().split(" ");

            boolean isShortCut = false;

            outerLoop: for (int i = 0; i < cmd.length; i++) {
                if (!shortcuts[cmd[i].toLowerCase().charAt(0)-97]) {
                    shortcuts[cmd[i].toLowerCase().charAt(0)-97] = true;
                    String target = String.valueOf(cmd[i].charAt(0));
                    String wrapped = "[" + target + "]";
                    cmd[i] = cmd[i].replaceFirst(target, wrapped);
                    isShortCut = true;
                    break outerLoop;
                }
            }
            if (!isShortCut) {
                 outerLoop: for (int i = 0; i < cmd.length; i++) {
                    for (int j = 1; j < cmd[i].length(); j++) {
                        if (!shortcuts[cmd[i].toLowerCase().charAt(j)-97]) {
                            shortcuts[cmd[i].toLowerCase().charAt(j)-97] = true;
                            String before = cmd[i].substring(0, j);
                            String cur = cmd[i].substring(j, j+1);
                            String after = cmd[i].substring(j+1);
                            cmd[i] = before + "[" + cur + "]" + after;
                            break outerLoop;
                        }
                    }
                }
            }

            for (int i = 0; i < cmd.length; i++) {
                sb.append(cmd[i]).append(" ");
            } sb.append('\n');
        }

        System.out.println(sb.toString());
        br.close();
    }

}
