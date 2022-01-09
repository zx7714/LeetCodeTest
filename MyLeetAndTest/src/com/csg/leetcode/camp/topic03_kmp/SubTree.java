package com.csg.leetcode.camp.topic03_kmp;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * 给定一个树tree1，tree2，判断tree2是否为tree1的子树
 * @author zhangxu
 *
 */
public class SubTree {
	public static class Node{
		int value;
		Node left;
		Node right;
		public Node(int value){
			this.value = value; 
		}
	}
	public static boolean subTree(Node node1,Node node2){
		if(node1 == null || node2 == null){
			return false;
		}
		List<String> str = getNodeArry(node1);
		List<String> match = getNodeArry(node2);
		
		if(str.size() == 0 || match.size() == 0|| match.size() > str.size()){
			return false;
		}
		int next[] = getNextArray(match);
		int x = 0;
		int y = 0;
		while(x < str.size() && y < match.size()){
			if(match.get(y).equals(str.get(x))){
				x++;
				y++;
			}else if(next[y] == -1){
				x++;
			}else{
				y = next[y];
			}
		}
		return y == match.size();
	}
	private static int[] getNextArray(List<String> match) {
		if(match.size() == 1){
			return new int[]{-1};
		}
		int next[] = new int[match.size()];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int cn = 0;
		while(index < match.size()){
			if(next[index-1] == next[cn]){
				next[index++] = ++cn;
			}else if(cn > 0){
				cn = next[cn];
			}else{
				next[index++] = 0;
			}
		}
		return next;
	}
	private static List<String> getNodeArry(Node node) {
		List<String> list = new ArrayList<>();
		process(node,list);
		return list;
	}
	private static void process(Node node, List<String> list) {
		if (node == null) {
			list.add("null");
		} else {
			list.add(String.valueOf(node.value));
			process(node.left, list);
			process(node.right, list);
		}
	}
	
	public static void main(String[] args) {
		Node node = new Node(1);
		node.left = new Node(2);
		node.left.left = new Node(3);
		
		Node node2 = new Node(1);
		
		System.out.println(subTree(node, node2));
	}
}
