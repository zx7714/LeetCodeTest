package com.csg.leetcode.algorithm.week05.rep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csg.leetcode.algorithm.week05.MaxDistanceBT.Node;

public class MaxDistanceBT_Rep_1 {
	public static int maxDistance1(Node head) {
		if(head == null) {
			return 0;
		}
		int max = 0;
		List<Node> nodeList = getPreNode(head);
		Map<Node,Node> parentMap = getParentMap(head);
		for(int i = 0; i < nodeList.size(); i++) {
			for(int j = i; j < nodeList.size(); j++) {
				max = Math.max(max, getMaxDistance(nodeList.get(i),nodeList.get(j),parentMap));
			}
			
		}
		return max;
	}

	private static int getMaxDistance(Node node, Node node2, Map<Node, Node> parentMap) {
		Set<Node> set = new HashSet<>();
		set.add(node);
		Node cur = node;
		while(parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			set.add(cur);
		}
		
		//找到公共祖先
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
			distance2++;
		}
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

	private static List<Node> getPreNode(Node head) {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(head);
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

	
	public static class Info{
		int maxDistance;
		int height;
		public Info(int distance,int height) {
			this.maxDistance = distance;
			this.height = height;
		}
	}
	private static int maxDistance2(Node head) {
		if(head == null) {
			return 0;
		}
		Info info = process(head);
		return info.maxDistance;
	}

	private static Info process(Node head) {
		if(head == null) {
			return new Info(0, 0);
		}
		
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		return new Info(
				Math.max(
					//当前数左右子树上的的最大距离
					Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
					//当前树上的最大距离
					leftInfo.height + rightInfo.height + 1
				),
				height);
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
//					System.out.println(maxDistance1(head));
//					System.out.println(maxDistance2(head));
					if (maxDistance1(head) != maxDistance2(head)) {
						System.out.println("Oops!");
					}
				}
				System.out.println("finish!");
				Node node = new Node(1);
				node.left = new Node(2);
				node.left.left = new Node(3);
				node.left.right = new Node(3);
//				
//				node.right = new Node(3);
//				node.right.left = new Node(3);
//				node.right.left.right = new Node(4);
//				
//				System.out.println(maxDistance1(node));
//				System.out.println(maxDistance2(node));
				
//				Node node = new Node(1);
//				node.right = new Node(2);
//				node.right.left = new Node(3);
//				node.right.left.right = new Node(4);
//				node.right.right = new Node(5);
//				node.right.right.right = new Node(6);
//				
//				System.out.println(maxDistance1(node));
//				System.out.println(maxDistance2(node));
			}

}
