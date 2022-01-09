package com.csg.leetcode.algorithm.week04;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeMaxWithRep01 {
	public static int whithMap(Tree head) {
		if(null == head) {
			return 0;
		}
		
		Queue<Tree> q = new LinkedList<>();
		Map<Tree,Integer> map = new HashMap<>();
		int curLevel = 1;
		int curLevelNodes = 0;
		
		int max = 0;
		q.add(head);
		
		map.put(head, curLevel);
		while(!q.isEmpty()) {
			Tree curNode = q.poll();
			int curNodeLevel = map.get(curNode);
			if(curNode.left != null) {
				q.add(curNode.left);
				map.put(curNode.left, curNodeLevel + 1);
			}
			if(curNode.right != null) {
				q.add(curNode.right);
				map.put(curNode.right, curNodeLevel + 1);
			}
			
			if(curNodeLevel == curLevel) {
				curLevelNodes ++;
			}else {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;
				curLevel++;
			}
			
		}
		max = Math.max(max, curLevelNodes);
		
		return max;
	}
	
	public static int maxWithOutMap(Tree head) {
		if(null == head){
			return 0;
		}
		
		Queue<Tree> q = new LinkedList<>();
		Tree curEnd = head;
		Tree nextEnd = null;
		int max = 0;
		q.add(head);
		int curLevelNodes = 0;
		
		while(!q.isEmpty()) {
			Tree curNode = q.poll();
			if(curNode.left != null) {
				nextEnd = curNode.left;
				q.add(curNode.left);
			}
			
			if(curNode.right != null) {
				nextEnd = curNode.right;
				q.add(curNode.right);
			}
			curLevelNodes ++;
			if(curNode== curEnd) {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		return max;
	}
	

	// for test
	public static Tree generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Tree generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Tree head = new Tree((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000_0000_00/6;
		for(int i = 0; i < 7; i++) {
			new Thread(()->{
				for (int j = 1; j < testTimes/6; j++) {
					Tree head = generateRandomBST(maxLevel, maxValue);
					if (whithMap(head) != maxWithOutMap(head)) {
						System.out.println("Oops!");
					}
				}
			}).start();
		}
		
		

		
		System.out.println("finish!");
		
	}

}
