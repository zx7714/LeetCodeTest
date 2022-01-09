package com.csg.leetcode.algorithm.week05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先。
 * 
 * @author zhangxu
 *
 */
public class LowestAncestor {
	
	
	public static Node lowestAncestor(Node head,Node o1,Node o2) {
		if(null == head ) {
			return null;
		}
		
		Map<Node,Node> parentMap = new HashMap<Node,Node>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		Set<Node> set = new HashSet<>();
		Node cur = o1;
		set.add(cur);
		while(parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			set.add(cur);
		}
		
		cur = o2;
		while(!set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		
		return cur;
	}
	
	public static void fillParentMap(Node node,Map<Node,Node> map){
		if(node.left != null) {
			map.put(node.left, node);
			fillParentMap(node.left, map);
		}
		
		if(node.right != null) {
			map.put(node.right, node);
			fillParentMap(node.right, map);
		}
	}
	
	public static class Info{
		Node ans;
		boolean findO1;
		boolean findO2;
		
		public Info(Node node,boolean findO1,boolean findO2) {
			this.ans = node;
			this.findO1 = findO1;
			this.findO2 = findO2;
		}
	}
	
	public static Node lowestAncestor2(Node x,Node o1,Node o2) {
		return process(x,o1,o2).ans;
	}

	private static Info process(Node x, Node o1, Node o2) {
		if(null == x) {
			return new Info(null, false, false);
		}
		
		Info left = process(x.left, o1, o2);
		Info right = process(x.right, o1, o2);
		
		boolean findO1 = x == o1 || left.findO1 || right.findO1;
		boolean findO2 = x == o2 || left.findO2	|| right.findO2;
		
		Node ans = null;
		if(left.ans != null) {
			ans = left.ans;
		}
		if(right.ans != null) {
			ans = right.ans;
		}
		if(ans == null) {
			if(findO1 && findO2) {
				ans = x;
			}
		}
		return new Info(ans, findO1, findO2);
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

		// for test
		public static Node pickRandomOne(Node head) {
			if (head == null) {
				return null;
			}
			ArrayList<Node> arr = new ArrayList<>();
			fillPrelist(head, arr);
			int randomIndex = (int) (Math.random() * arr.size());
			return arr.get(randomIndex);
		}

		// for test
		public static void fillPrelist(Node head, ArrayList<Node> arr) {
			if (head == null) {
				return;
			}
			arr.add(head);
			fillPrelist(head.left, arr);
			fillPrelist(head.right, arr);
		}

		public static void main(String[] args) {
			int maxLevel = 4;
			int maxValue = 100;
			int testTimes = 1000000;
			for (int i = 0; i < testTimes; i++) {
				Node head = generateRandomBST(maxLevel, maxValue);
				Node o1 = pickRandomOne(head);
				Node o2 = pickRandomOne(head);
				Node o11 = lowestAncestor(head, o1, o2);
				Node o22 = lowestAncestor2(head, o1, o2);
				if (o11 != o22) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
			Node n1 = new Node(34);
			n1.right = new Node(48);
			n1.right.right = new Node(84);
		}
}
