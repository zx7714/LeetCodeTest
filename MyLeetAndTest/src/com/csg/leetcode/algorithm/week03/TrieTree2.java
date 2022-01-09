package com.csg.leetcode.algorithm.week03;

public class TrieTree2 {
	public static class Node{
		int pass;
		int end;
		Node nodes[];
		public Node() {
			pass = 0;
			end = 0;
			nodes = new Node[26];
		}
	}
	
	Node root;
	public TrieTree2() {}{
		root = new Node();
	}
	public void  insert(String word) {
		if(null == word)
			return;
		Node node = root;
		node.pass++;
		char[] chars = word.toCharArray();
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index = chars[i] - 'a';
			if(node.nodes[index] == null) {
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
		int idx = 0;
		for (int i = 0; i < chars.length; i++) {
			idx = chars[i] - 'a';
			if(--node.nodes[idx].pass == 0) {
				node.nodes[idx] = null;
				return;
			}
			node = node.nodes[idx];
		}
		node.end--;
	}
	
	public int search(String word) {
		if(null == word)
			return 0;
		Node node = root;
		char[] chars = word.toCharArray();
		int idx = 0;
		for (int i = 0; i < chars.length; i++) {
			idx = chars[i] - 'a';
			if(node.nodes[idx] == null) {
				return 0;
			}
			node = node.nodes[idx];
		}
		return node.end;
	}
	
	public void delete2(String word) {
		if(word == null)
			return;
		if(search(word)==0)
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
		node.end --;
	}
	public void delete3(String word) {
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
}
