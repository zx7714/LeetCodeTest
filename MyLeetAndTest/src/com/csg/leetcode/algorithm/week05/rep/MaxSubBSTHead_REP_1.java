package com.csg.leetcode.algorithm.week05.rep;

import java.util.ArrayList;
import java.util.List;

import com.csg.leetcode.algorithm.week05.Node;

public class MaxSubBSTHead_REP_1 {
	public static Node maxBstHead1(Node head) {
		if(head == null) {
			return null;
		}
		if(getBSTSize(head) != 0) {
			return head;
		}
		
		Node left =  maxBstHead1(head.left);
		Node right = maxBstHead1(head.right);
		
		return getBSTSize(left) >= getBSTSize(right) ? left : right;
	}
	

	private static int getBSTSize(Node head) {
		if(head == null) {
			return 0;
		}
		List<Node> nodeList = new ArrayList<Node>();
		in(head,nodeList);
		
		for(int i = 1;i < nodeList.size();i++) {
			if(nodeList.get(i).value <= nodeList.get(i-1).value) {
				return 0;
			}
		}
		return nodeList.size();
	}

	private static void in(Node head, List<Node> nodeList) {
		if(head == null) {
			return;
		}
		in(head.left,nodeList);
		nodeList.add(head);
		in(head.right,nodeList);
	}

	
	public static class Info{
		Node maxBstHead;
		int maxBstSize;
		int max;
		int min;
		public Info(Node head,int size,int max,int min) {
			this.maxBstHead = head;
			this.maxBstSize = size;
			this.max = max;
			this.min = min;
		}
	}
	
	public static Node maxBstHead2(Node head) {
		if(head == null) {
			return head;
		}
		Info info = process(head);
		return info.maxBstHead;
	}


	private static Info process(Node head) {
		if(head == null ) {
			return null;
		}
		Info left = process(head.left);
		Info right = process(head.right);
		
		int max = head.value;
		int min = head.value;
		Node maxBstHead = null;
		int maxBstSize = 0;
		if(left != null) {
			max = Math.max(max, left.max);
			min = Math.min(min, left.min);
			maxBstHead = left.maxBstHead;
			maxBstSize = left.maxBstSize;
		}
		
		if(right != null) {
			max = Math.max(max, right.max);
			min = Math.min(min, right.min);
			if(right.maxBstSize > maxBstSize) {
				maxBstHead = right.maxBstHead;
				maxBstSize = right.maxBstSize;
			}
		}
		
		if((left == null ? true : (
					(left.maxBstHead == head.left) 
						&& 
					(head.value > left.max)))
				&&
			(right == null ? true : (
					(right.maxBstHead == head.right)
						&&
					(head.value < right.min)
					))
		) {
			maxBstHead = head;
			maxBstSize = (left == null ? 0 : left.maxBstSize) + 
							(right == null ? 0 : right.maxBstSize)+
							1;
		}
		
		return new Info(maxBstHead, maxBstSize, max, min);
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
				Node maxBSTHead1 = maxBstHead1(head);
				Node maxBSTHead2 = maxBstHead2(head);
//				System.out.println(maxBSTHead1.value);
//				System.out.println(maxBSTHead2.value);
				if ( maxBSTHead1 != maxBSTHead2) {
					System.out.println();
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
			
			Node node = new Node(71);
			node.right = new Node(78);
			node.right.left = new Node(90);
			node.right.right = new Node(1);
			node.right.right.right = new Node(2);
			
			Node maxBSTHead1 = maxBstHead1(node);
			Node maxBSTHead2 = maxBstHead2(node);
			System.out.println(maxBSTHead1.value);
			System.out.println(maxBSTHead2.value);
		}
}
