import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		Tree tree = new Tree();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char mid = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree.add(mid, left, right);
		}
		
		Node root = tree.getRoot();
		tree.preOrder(root);
		sb.append('\n');
		tree.inOrder(root);
		sb.append('\n');
		tree.postOrder(root);
		sb.append('\n');
		
		System.out.println(sb);
	}
	
	static class Node {
		char value;
		Node left, right;
		Node (char value) { this.value = value; }
	}
	
	static class Tree {
		private Map<Character, Node> tree = new HashMap<>();
		
		public void add(char root, char left, char right) {
			tree.putIfAbsent(root, new Node(root));
			
			Node pNode = tree.get(root);
			
			if (left != '.') {
				tree.putIfAbsent(left, new Node(left));
				pNode.left = tree.get(left);
			}
			
			if (right != '.') {
				tree.putIfAbsent(right, new Node(right));
				pNode.right = tree.get(right);
			}
		}
		
		public void preOrder(Node node) {
			if (node == null) return;
			
			sb.append(node.value);
			preOrder(node.left);
			preOrder(node.right);
		}
		
		public void inOrder(Node node) {
			if (node == null) return;
			
			inOrder(node.left);
			sb.append(node.value);
			inOrder(node.right);
		}
		
		public void postOrder(Node node) {
			if (node == null) return;
			
			postOrder(node.left);
			postOrder(node.right);
			sb.append(node.value);
		}	
		
		public Node getRoot() { return tree.get('A'); }
		
	}
}