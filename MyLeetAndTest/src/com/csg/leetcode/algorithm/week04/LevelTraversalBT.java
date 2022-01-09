package com.csg.leetcode.algorithm.week04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树水平遍历，宽度优先遍历
 * @author zhangxu
 *
 */
public class LevelTraversalBT {
	public static void traversal(Tree head){
		Queue<Tree> q = new LinkedList<>();
		q.add(head);
		while(!q.isEmpty()) {
			head = q.poll();
			System.out.println(head.value);
			if(head.left != null) {
				q.add(head.left);
			}
			if(head.right != null) {
				q.add(head.right);
			}
		}
	}
	public static void main(String[] args) {
		Tree head = new Tree(1);
		head.left = new Tree(2);
		head.right = new Tree(3);
		head.left.left = new Tree(4);
		head.left.right = new Tree(5);
		head.right.left = new Tree(6);
		head.right.right = new Tree(7);

		traversal(head);
		System.out.println("========");
	}

}
