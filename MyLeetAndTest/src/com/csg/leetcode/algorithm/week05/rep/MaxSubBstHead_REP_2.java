package com.csg.leetcode.algorithm.week05.rep;

import java.util.ArrayList;
import java.util.List;

import com.csg.leetcode.algorithm.week05.Node;

public class MaxSubBstHead_REP_2 {
	public static Node maxBstHead1(Node head) {
		if(head == null) {
			return null;
		}
		if(getBstSize(head) != 0) {
			return head;
		}
		
		Node leftAns = maxBstHead1(head.left);
		Node rightAns = maxBstHead1(head.right);
		
		return getBstSize(leftAns) >= getBstSize(rightAns) ? leftAns : rightAns;
 	}

	private static int getBstSize(Node head) {
		List<Node> nodeList = new ArrayList<Node>();
		in(head,nodeList);
		
		for(int i = 1;i<nodeList.size();i++) {
			if(nodeList.get(i).value < nodeList.get(i-1).value) {
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
		in(head.right, nodeList);
	}

	
	public static class Info{
		Node maxSubNode;
		int maxSubSize;
		int max;
		int min;
		public Info(Node node,int size,int max,int min) {
			this.maxSubNode = node;
			this.maxSubSize = size;
			this.max = max;
			this.min = min;
		}
	}

	public static Node maxBstHead2(Node head) {
		if(null == head) {
			return head;
		}
		Info info = process(head);
		return info.maxSubNode;
	}
	
	private static Info process(Node head) {
		if(head == null) {
			return null;
		}
		Info left = process(head.left);
		Info right = process(head.right);
		
		int max = head.value;
		int min = head.value;
		Node maxSubNode = null;
		int maxSubSize = 0;
		if(left != null) {
			max = Math.max(max, left.max);
			min = Math.min(min, left.min);
			maxSubNode = left.maxSubNode;
			maxSubSize = left.maxSubSize;
		}
		if(right != null) {
			max = Math.max(max, right.max);
			min = Math.min(right.min, min);
			if(right.maxSubSize > maxSubSize) {
				maxSubNode = right.maxSubNode;
				maxSubSize = right.maxSubSize;
			}
		}

		if((left == null ? true : (left.maxSubNode == head.left && left.max < head.value))
				&&
			(right == null ? true : (right.maxSubNode == head.right && right.min > head.value))
		) {
			maxSubNode = head;
			maxSubSize = (left == null ? 0 : left.maxSubSize) + 
						(right == null ? 0 : right.maxSubSize) +
						1;
		}
		return new Info(maxSubNode, maxSubSize, max, min);
		
	}

	public static void main(String[] args) {

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
