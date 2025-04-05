import java.io.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		
		String[] in = new String[3];
		
		for (int i = 0; i < 3; i++) {
			in[i] = br.readLine();
		}
		
		int lastNumber = 0;
		int lastIdx = 0;
		for (int i = 0; i < 3; i++) {
			if (in[i].equals("Fizz") || in[i].equals("Buzz") || in[i].equals("FizzBuzz")) continue;
			else {
				lastNumber = Integer.parseInt(in[i]);
				lastIdx = i;
			}
		}

		lastNumber += (3-lastIdx);
		
		if (lastNumber%3==0 && lastNumber%5==0) System.out.println("FizzBuzz");
		else if (lastNumber%3==0 && lastNumber%5!=0) System.out.println("Fizz");
		else if (lastNumber%3!=0 && lastNumber%5==0) System.out.println("Buzz");
		else if (lastNumber%3!=0 && lastNumber%5!=0) System.out.println(lastNumber);
	}
}
