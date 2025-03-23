import java.io.*;
import java.util.*;

public class Main {

	static class User implements Comparable<User> {
		int age;
		String name;
		int order;
		
		User(int age, String name, int order) {
			this.age = age;
			this.name = name;
			this.order = order;
		}
		
		@Override
		public int compareTo(Main.User o) {
			if (this.age != o.age) return this.age - o.age;
			return this.order - o.order;
		}
	}
	
	static int N;
	static List<User> users = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			users.add(new User(age, name, i));
		}
		
		Collections.sort(users);
		
		for (User user : users) {
			System.out.println(user.age + " " + user.name);
		}
		
	}
	
	
}