package com.csg.leetcode.algorithm.week05;

import java.util.ArrayList;
import java.util.List;

public class IsBinarySearchTree {
	public static boolean isBST1(Node head) {
		if(head == null) {
			return true;
		}
		List<Node> nodeList = new ArrayList<Node>();
		in(head,nodeList);
		
		for(int i = 1;i<nodeList.size();i++) {
			if(nodeList.get(i).value <= nodeList.get(i-1).value) {
				return false;
			}
		}
		
		return true;
	}

	private static void in(Node head, List<Node> nodeList) {
		if(head == null) {
			return;
		}
		in(head.left, nodeList);
		nodeList.add(head);
		in(head.right,nodeList);
	}

	public static class Info{
		boolean isBST;
		int max;
		int min;
		public Info(boolean isBST,int max,int min) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}
	}
	
	public static boolean isBST2(Node head) {
		if(head == null) {
			return true;
		}
		Info info = process(head);
		return info.isBST;
	}

	private static Info process(Node head) {
		if(head == null) {
			return null;
		}
		
		Info left = process(head.left);
		Info right = process(head.right);
		int min = head.value;
		int max = head.value;
		if(left != null) {
			min = Math.min(min, left.min);
			max = Math.max(max, left.max);
		}
		if(right != null) {
			min = Math.min(min, right.min);
			max = Math.max(max, right.max);
		}
		boolean isBst = false;
		if(
			(left == null ? true : (left.isBST && left.max < head.value))
			&&
			(right == null ? true : (right.isBST && right.min > head.value))
		) {
			isBst = true;
		}
		
		return new Info(isBst, max, min);
	}
	
	
	// for test
		public static Node generateRandomBST(int maxLevel, int maxValue) {
			return generate(1, maxLevel, maxValue);
		}

		// for test
		public static Node generate(int level, int maxLevel, int maxValue) {
			if (level > maxLevel || Math.random() < 0.5) {
				return null;
			}
			Node head = new Node((int) (Math.random() * maxValue));
			head.left = generate(level + 1, maxLevel, maxValue);
			head.right = generate(level + 1, maxLevel, maxValue);
			return head;
		}

		public static void main(String[] args) {
			int maxLevel = 4;
			int maxValue = 100;
			int testTimes = 1000000;
			for (int i = 0; i < testTimes; i++) {
				Node head = generateRandomBST(maxLevel, maxValue);
				if (isBST1(head) != isBST2(head)) {
					System.out.println(isBST1(head));
					System.out.println(isBST2(head));
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
//			Node head = new Node(12);
//			head.right = new Node(56);
//			head.right.right = new Node(9);
//			head.right.right.right = new Node(58);
//			System.out.println(isBST1(head));
//			System.out.println(isBST2(head));
		
		}

}
