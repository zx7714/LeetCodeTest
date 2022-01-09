package com.csg.leetcode.algorithm.week04;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树的最大宽度
 * @author zhangxu
 *
 */
public class TreeMaxWidth {
	/**
	 * 通过map记录每个节点的所在层数
	 * @param head
	 * @return
	 */
	public static int maxWhithMap(Tree head){
		if(null == head) {
			return 0;
		}
		Queue<Tree> q = new LinkedList<>();
		Map<Tree,Integer> map = new HashMap<>();
		int curLevel = 1; //当前正在统计的成熟
		int curLevelNodes = 0; //当前统计层数的宽度
		int max = 0;
		q.add(head);
		map.put(head, curLevel);
		while(!q.isEmpty()) {
			Tree cur = q.poll();
			int curNodeLevel = map.get(cur);//当前节点是第几层
			if(cur.left != null) {
				map.put(cur.left, curNodeLevel + 1);
				q.add(cur.left);
			}
			
			if(cur.right != null) {
				map.put(cur.right, curNodeLevel + 1);
				q.add(cur.right);
			}
			if(curLevel == curNodeLevel) {
				curLevelNodes ++;
			}else {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;
				curLevel ++; //开始处理下一层
			}
		}
		max = Math.max(max, curLevelNodes);
		return max;
	}
	
	/**
	 * 1.使用两个变量，记录当前层数的结束和下一层数的结束
	 * 2.如果队列中弹出的节点是当前层数的结束，表示下一个节点就是下一层了，结算。
	 * 3.更换当前层结束节点。
	 * 
	 * @param head
	 * @return
	 */
	public static int maxWithoutMap(Tree head){
		if(null == head) {
			return 0;
		}
		
		Queue<Tree> q = new LinkedList<>();
		Tree curEnd = head;
		Tree nextEnd = null;
		int max = 0;
		q.add(head);
		int curLevelNodes = 0; //当前层节点数，即宽度
		
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
			curLevelNodes++;
			if(curNode == curEnd) {
				max = Math.max(max, curLevelNodes);
				curEnd = nextEnd;
				curLevelNodes = 0;
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
				if (maxWhithMap(head) != maxWithoutMap(head)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");

		}

}
