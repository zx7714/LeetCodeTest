package com.csg.leetcode.algorithm.week05.rep;

import java.util.ArrayList;
import java.util.List;

import com.csg.leetcode.algorithm.week05.Node;

public class IsBinarySearchTree_Rep_1 {
	public static boolean isBst1(Node head) {
		if(head == null) {
			return true;
		}
		
		List<Node> nodes = new ArrayList<Node>();
		in(head,nodes);
		for(int i = 1 ; i< nodes.size() ; i++) {
			if(nodes.get(i).value <= nodes.get(i-1).value) {
				return false;
			}
		}
		return true;
	}
	private static void in(Node head, List<Node> nodes) {
		if(head == null ) {
			return;
		}
		
		in(head.left,nodes);
		nodes.add(head);
		in(head.right,nodes);
	}
	
	public static class Info{
		boolean isBst;
		int max;
		int min;
		public Info(boolean is,int max,int min) {
			this.isBst = is;
			this.max = max;
			this.min = min;
		}
	}
	public static boolean isBst2(Node head) {
		if(head == null) {
			return true;
		}
		
		Info info = process(head);
		return info.isBst;
	}
	private static Info process(Node head) {
		if(head == null ) {
			return null;
		}
		
		Info left =  process(head.left);
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
		
		boolean flag = false;
		if( (left == null ? true : 
			(left.isBst && 
					head.value > left.max))
			&&
			(right == null ? true : 
				(right.isBst && 
						head.value < right.min))
		) {
			flag = true;
		}
		return new Info(flag, max, min);
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
					System.out.println(isBst1(head));
					System.out.println(isBst2(head));
					if (isBst1(head) != isBst2(head)) {
						System.out.println(isBst1(head));
						System.out.println(isBst2(head));
						System.out.println("Oops!");
					}
				}
				System.out.println("finish!");
//				Node head = new Node(12);
//				head.right = new Node(56);
//				head.right.right = new Node(9);
//				head.right.right.right = new Node(58);
//				System.out.println(isBST1(head));
//				System.out.println(isBST2(head));
			
			}

}
