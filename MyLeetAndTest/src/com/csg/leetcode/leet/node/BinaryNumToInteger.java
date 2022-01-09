package com.csg.leetcode.leet.node;

public class BinaryNumToInteger {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	public static int getDecimalValue(ListNode head) {
		
		return process(head)[1];
	}
	
	//0 ->倒数第几个节点 1 -> 结果和
	public static int[] process(ListNode node) {
		if(node == null) {
			return new int[]{0,0};
		}
		int[] result = process(node.next);
		return new int[] { result[0] + 1, (int) (node.val * Math.pow(2, result[0])+result[1])};
	}
	
	
	public static int getDecimalValue2(ListNode head) {
		int ans = 0;
		while(head != null) {
			ans = (ans << 1) + head.val;
			head = head.next;
		}
		return ans;
	}
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(0);
		node.next.next = new ListNode(1);
		System.out.println(getDecimalValue2(node));
		
	}
}
