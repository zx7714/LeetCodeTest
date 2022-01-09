package com.csg.leetcode.algorithm.week05.rep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csg.leetcode.algorithm.week05.Node;

public class MaxDistanceBT_rep_3 {
	public static int maxDistance1(Node head) {
		if(head == null) {
			return 0;
		}
		
		List<Node> nodeList = getNodeList(head);
		Map<Node,Node> parentMap = getParentMap(head);
		int max = 0;
		for(int i = 0;i<nodeList.size();i++) {
			for(int j = 0;j < nodeList.size();j++){
				max = Math.max(max, getMapDistance(nodeList.get(i),nodeList.get(j),parentMap));
			}
		}
		return max;
	}

	private static int getMapDistance(Node node, Node node2, Map<Node, Node> parentMap) {
		
		Set<Node> set = new HashSet<Node>();
		Node cur = node;
		set.add(cur);
		while(null != parentMap.get(cur)) {
			cur = parentMap.get(cur);
			set.add(cur);
		}
		
		cur = node2;
		while(!set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		
		Node lowestParent = cur;
		
		cur = node;
		int distance1 = 1;
		while(cur != lowestParent) {
			cur = parentMap.get(cur);
			distance1++;
		}
		
		cur = node2;
		int distance2 = 1;
		while(cur != lowestParent) {
			cur = parentMap.get(cur);
			distance2 ++;
		}
		return distance1 + distance2 -1;
	}

	private static Map<Node, Node> getParentMap(Node head) {
		Map<Node,Node> map = new HashMap<Node,Node>();
		map.put(head, null);
		fillParentMap(head,map);
		return map;
	}
	

	private static void fillParentMap(Node head, Map<Node, Node> map) {
		if(head.left != null) {
			map.put(head.left, head);
			fillParentMap(head.left, map);
		}
		if(head.right != null) {
			map.put(head.right, head);
			fillParentMap(head.right, map);
		}
	}

	private static List<Node> getNodeList(Node head) {
		List<Node> nodeList = new ArrayList<>();
		in(head,nodeList);
		return nodeList;
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
		int maxDistance;
		int height;
		public Info(int maxDistance,int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}
	}
	
	public static int  maxDistance2(Node head) {
		if(null == head) {
			return 0;
		}
		Info info = process(head);
		return info.maxDistance;
	}

	private static Info process(Node head) {
		if(head == null) {
			return new Info(0,0);
		}
		
		Info left = process(head.left);
		Info right = process(head.right);
		
		int height = Math.max(left.height, right.height) + 1;
		
		int maxDistance = Math.max(
				Math.max(left.maxDistance, right.maxDistance), 
				left.height + right.height + 1);
		
		return new Info(maxDistance,height);
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
//		int maxLevel = 4;
//		int maxValue = 100;
//		int testTimes = 1000000;
//		for (int i = 0; i < testTimes; i++) {
//			Node head = generateRandomBST(maxLevel, maxValue);
//			if (maxDistance1(head) != maxDistance2(head)) {
//				System.out.println(maxDistance1(head));
//				System.out.println(maxDistance2(head));
//				System.out.println("Oops!");
//			}
//		}
		System.out.println("finish!");
//		Node node = new Node(1);
//		node.left = new Node(2);
//		node.left.left = new Node(3);
//		node.left.right = new Node(3);
		
//		node.right = new Node(3);
//		node.right.left = new Node(3);
//		node.right.left.right = new Node(4);
//		
//		System.out.println(maxDistance1(node));
//		System.out.println(maxDistance2(node));

//		Node node = new Node(1);
//		node.right = new Node(2);
//		node.right.left = new Node(3);
//		node.right.left.right = new Node(4);
//		node.right.right = new Node(5);
//		node.right.right.right = new Node(6);

//		System.out.println(maxDistance1(node));
//		System.out.println(maxDistance2(node));
		Node node = new Node(1);
		node.right = new Node(2);
		node.right.left = new Node(3);
		node.right.left.right = new Node(5);
		node.right.right = new Node(4);
		node.right.right.right = new Node(5);
		
		
		System.out.println(maxDistance1(node));
		System.out.println(maxDistance2(node));
	}

}
