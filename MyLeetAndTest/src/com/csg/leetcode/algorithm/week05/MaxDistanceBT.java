package com.csg.leetcode.algorithm.week05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
 * 返回整课二叉树的最大距离——距离最远的两个子叶。
 * @author zhangxu
 *
 */
public class MaxDistanceBT {
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int val) {
			value = val;
		}
	}
	
	public static int maxDistance(Node head) {
		if(null == head) {
			return 0;
		}
		int max = 0;
		//把所有节点放入list中。后续挨个找每个节点的距离
		List<Node> nodeList = getPreList(head);
		Map<Node,Node> parentMap = getParentMap(head);
		
		for (int i = 0; i < nodeList.size(); i++) {
			for(int j = i;j<nodeList.size();j++) {
				max = Math.max(max, getDistance(nodeList.get(i),nodeList.get(j),parentMap));
						
			}
			
		}
		
		return max;
	}

	private static int getDistance(Node node, Node node2, Map<Node, Node> parentMap) {
		Set<Node> set = new HashSet<>();
		Node cur = node;
		set.add(cur);
		//把当前节点及父节点放入set
		while(parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			set.add(cur);
		}
		cur = node2;
		//找到第一个公共父节点
		while(!set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		
		Node lowestParent = cur;
		cur = node;
		// 计算o1到公共父节点的距离
		int distance1 = 1;
		while(cur != lowestParent) {
			distance1++;
			cur = parentMap.get(cur);
		}
		// 计算o2到公共父节点的距离
		cur = node2;
		int distance2 = 1;
		while(cur != lowestParent) {
			distance2++;
			cur = parentMap.get(cur);
		}
		// 返回总距离
		return distance1 + distance2 - 1;
	}

	private static Map<Node, Node> getParentMap(Node head) {
		Map<Node,Node> parentMap = new HashMap<Node,Node>();
		parentMap.put(head, null);

		fillParentMap(head,parentMap);
		return parentMap;
	}

	private static void fillParentMap(Node head, Map<Node, Node> parentMap) {
		if(head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		
		if(head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	private static List<Node> getPreList(Node head) {
		List<Node> nodeList = new ArrayList<>();
		fillPreList(head,nodeList);
		return nodeList;
	}

	private static void fillPreList(Node head, List<Node> nodeList) {
		nodeList.add(head);
		if(head.left != null) {
			fillPreList(head.left, nodeList);
		}
		if(head.right != null) {
			fillPreList(head.right, nodeList);
		}
	}
	
	public static class Info {
		int maxDistance;
		int height;
		public Info(int distance,int height) {
			this.maxDistance = distance;
			this.height = height;
		}
	}
	
	public static int maxDistance2(Node head) {
		return process(head).maxDistance;
	}

	private static Info process(Node head) {
		if(head == null) {
			return new Info(0, 0);
		}
		
		Info left = process(head.left);
		Info right =process(head.right);
		int height = Math.max(left.height, right.height) + 1;
		
		return new Info(Math.max(
				Math.max(left.maxDistance, right.maxDistance),
				left.height + right.height + 1
				), height);
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
				if (maxDistance(head) != maxDistance2(head)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
			Node node = new Node(1);
			node.left = new Node(2);
			node.right = new Node(3);
			System.out.println(maxDistance(node));
			System.out.println(maxDistance2(node));
		}

	
}
