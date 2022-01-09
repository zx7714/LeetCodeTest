package com.csg.leetcode.algorithm.week04;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeMaxWithRep02 {
	public static int maxWithMap(Tree head){
		if(null == head) {
			return 0;
		}
		int max = 0;
		Queue<Tree> queue = new LinkedList<>();
		Map<Tree,Integer> map = new HashMap<>();
		int curLevel = 1;
		int curLevelNodes = 0;
		map.put(head, curLevel);
		queue.add(head);
		while(!queue.isEmpty()) {
			Tree cur = queue.poll();
			int curNodeLevel = map.get(cur);
			if(cur.left != null) {
				queue.add(cur.left);
				map.put(cur.left, curNodeLevel + 1);
			}
			
			if(cur.right != null) {
				queue.add(cur.right);
				map.put(cur.right, curNodeLevel + 1);
			}
			if(curLevel == curNodeLevel) {
				curLevelNodes ++;
			}else {
				max = Math.max(curLevelNodes, max);
				curLevel ++;
				curLevelNodes = 1;
			}
		}
		max = Math.max(curLevelNodes, max);
		return max;
	}
	
	public static int maxWithoutMap(Tree head) {
		if(head == null) {
			return 0;
		}
		
		int max = 0;
		Queue<Tree> queue = new LinkedList<>();
		Tree curEnd = head;
		Tree nextEnd = null;
		int curLevlNodes = 0;
		queue.add(head);
		
		while(!queue.isEmpty()) {
			Tree cur = queue.poll();
			if(cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			
			if(cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevlNodes ++;
			if(cur == curEnd) {
				max = Math.max(max, curLevlNodes);
				curEnd = nextEnd;
				curLevlNodes = 0;
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
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Tree head = generateRandomBST(maxLevel, maxValue);
			if (maxWithMap(head) != maxWithoutMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}
}
