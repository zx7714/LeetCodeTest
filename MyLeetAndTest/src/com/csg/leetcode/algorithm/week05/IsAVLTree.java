package com.csg.leetcode.algorithm.week05;

import com.csg.leetcode.algorithm.week04.Tree;

public class IsAVLTree {
	
	public static class Result{
		int deep;
		int value;
		boolean isBalance;
		public Result(int deep,int value ,boolean isBalance) {
			this.deep = deep;
			this.isBalance = isBalance;
			this.value = value;
		}
	}
	
	public static boolean avlTree(Tree tree) {
		Result res = process(tree,1);
		return res.isBalance;
	}

	private static Result process(Tree tree,int deep) {
		Result leftRes = null;
		
		if(tree.left == null && tree.right ==null) {
			return new Result(deep,tree.value,true);
		}
		
		if(tree.left != null) { //base case
			leftRes = process(tree.left, deep+1);
		}
		Result rightRes = null;
		if(tree.right != null) {
			rightRes = process(tree.right, deep+1);
		}
		
		
		int deepLeft = leftRes == null ? deep : leftRes.deep;
		int deepRight = rightRes == null ? deep : rightRes.deep;
		
		boolean leftBalance = leftRes == null ? true : leftRes.isBalance;
		boolean rightBalance = rightRes == null ? true : rightRes.isBalance;
		return new Result(
				Math.max(deepLeft, deepRight), 
				tree.value,
				(leftRes == null || tree.value >= leftRes.value)
				&&
				(rightRes == null || tree.value <= rightRes.value)
				&&
				leftBalance && rightBalance
				&&
				Math.abs(deepLeft - deepRight) <= 1
				);
	}
	
	
	public static void main(String[] args) {
		Tree t1 = new Tree(5);
		t1.left = new Tree(4);
		t1.right = new Tree(8);
		t1.right.left = new Tree(7);
		t1.right.left.left = new Tree(6);
		t1.right.right = new Tree(9);
		t1.right.right.right = new Tree(10);
		//t1.right.right.right.right = new Tree(11);
		System.out.println("t1:"+avlTree(t1.right));
	}
}
