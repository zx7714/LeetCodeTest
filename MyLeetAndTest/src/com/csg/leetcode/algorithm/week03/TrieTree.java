package com.csg.leetcode.algorithm.week03;

import java.util.HashMap;

/**
 * 前缀树
 * @author zhangxu
 *
 */
public class TrieTree {
	public static class Node{
		int pass;
		int end;
		Node[] nodes;
		public Node(){
			pass = 0;
			end = 0;
			nodes = new Node[26];
		}
	}
	
	Node root;
	public TrieTree() {
		root = new Node();
	}
	public void insert(String word) {
		if(null == word)
			return;
		char[] chars = word.toCharArray();
		Node node = root;
		node.pass++;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(null == node.nodes[index] ) {
				node.nodes[index] = new Node();
			}
			node = node.nodes[index];
			node.pass++;
		}
		node.end++;
	}
	
	public void delete(String word) {
		if(null == word)
			return;
		if(search(word) == 0)
			return;
		Node node = root;
		char[] chars = word.toCharArray();
		node.pass--;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(--node.nodes[index].pass == 0) {
				node.nodes[index] = null;
				return;
			}
			node = node.nodes[index];
		}
		node.end--;
	}
	
	
	public int search(String word) {
		if(null == word) 
			return 0;
		Node node = root;
		char[] chars = word.toCharArray();
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(null == node.nodes[index]) {
				return 0;
			}
			node = node.nodes[index];
		}
		return node.end;
	}
	
	public int prefixNumber(String word) {
		if(null == word)
			return 0;
		Node node = root;
		char[] chars = word.toCharArray();
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(null == node.nodes[index]) {
				return 0;
			}
			node = node.nodes[index];
		}
		return node.pass;
	}
	public static class Right {

		private HashMap<String, Integer> box;

		public Right() {
			box = new HashMap<>();
		}

		public void insert(String word) {
			if (!box.containsKey(word)) {
				box.put(word, 1);
			} else {
				box.put(word, box.get(word) + 1);
			}
		}

		public void delete(String word) {
			if (box.containsKey(word)) {
				if (box.get(word) == 1) {
					box.remove(word);
				} else {
					box.put(word, box.get(word) - 1);
				}
			}
		}

		public int search(String word) {
			if (!box.containsKey(word)) {
				return 0;
			} else {
				return box.get(word);
			}
		}

		public int prefixNumber(String pre) {
			int count = 0;
			for (String cur : box.keySet()) {
				if (cur.startsWith(pre)) {
					count += box.get(cur);
				}
			}
			return count;
		}
	}
	
	// for test
		public static String generateRandomString(int strLen) {
			char[] ans = new char[(int) (Math.random() * strLen) + 1];
			for (int i = 0; i < ans.length; i++) {
				int value = (int) (Math.random() * 6);
				ans[i] = (char) (97 + value);
			}
			return String.valueOf(ans);
		}

		// for test
		public static String[] generateRandomStringArray(int arrLen, int strLen) {
			String[] ans = new String[(int) (Math.random() * arrLen) + 1];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = generateRandomString(strLen);
			}
			return ans;
		}

		public static void main(String[] args) {
			int arrLen = 100;
			int strLen = 20;
			int testTimes = 100000;
			for (int i = 0; i < testTimes; i++) {
				String[] arr = generateRandomStringArray(arrLen, strLen);
				TrieTree trie1 = new TrieTree();
				Right right = new Right();
				for (int j = 0; j < arr.length; j++) {
					double decide = Math.random();
					if (decide < 0.25) {
						trie1.insert(arr[j]);
//						trie2.insert(arr[j]);
						right.insert(arr[j]);
					} else if (decide < 0.5) {
						trie1.delete(arr[j]);
//						trie2.delete(arr[j]);
						right.delete(arr[j]);
					} else if (decide < 0.75) {
						int ans1 = trie1.search(arr[j]);
//						int ans2 = trie2.search(arr[j]);
						int ans3 = right.search(arr[j]);
						if (ans1 != ans3) {
							System.out.println("Oops!");
						}
					} else {
						int ans1 = trie1.prefixNumber(arr[j]);
//						int ans2 = trie2.prefixNumber(arr[j]);
						int ans3 = right.prefixNumber(arr[j]);
						if (ans1 != ans3) {
							System.out.println("Oops!");
						}
					}
				}
			}
			System.out.println("finish!");

		}
}
