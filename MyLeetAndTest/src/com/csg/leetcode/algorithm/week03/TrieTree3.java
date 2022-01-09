package com.csg.leetcode.algorithm.week03;

public class TrieTree3 {
	public static class Node{
		int pass;
		int end;
		Node[] nodes;
		public Node() {
			pass = 0;
			end = 0;
		}
	}
	
	private Node root;
	
	public void insert(String word) {
		if(null == word) {
			return;
		}
		char[] chars = word.toCharArray();
		Node node = root;
		//nodes数组的位置。
		int index = 0;
		node.pass++;

		for (int i = 0; i < chars.length; i++) {
			index = chars[i] -'a';
			if(node.nodes[index] == null) {
				node.nodes[index] = new Node();
			}
			node = node.nodes[index];
			node.pass++;
		}
		node.end++;
	}
	
	public void delete(String word) {
		if(null == word) {
			return;
		}
		if(search(word) == 0) {
			return;
		}
		Node node = root;
		char[] chars = word.toCharArray();
		node.pass--;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			//如果下个节点经过0次，那么就把它设置为null。
			if(--node.nodes[index].pass == 0) {
				node.nodes[index] = null;
				return;
			}
			node = node.nodes[index];
		}
		node.end --;
	}
	
	public int search (String word) {
		if(null == word) {
			return 0;
		}
		
		Node node = root;
		int index = 0;
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(node.nodes[index] == null) {
				return 0;
			}
			node = node.nodes[index];
		}
		return node.end;
	}
}
