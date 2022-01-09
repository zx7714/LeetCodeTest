package com.csg.leetcode.algorithm.week01;

//反转单向链表
public class ReverseSingleNode {
	
	public static void main(String[] args) {
		testreverseSingleNode();
	}
	
	public static void testreverseSingleNode(){
		SingleNode node = new ReverseSingleNode.SingleNode();
		node.value = "1";
		SingleNode add = node;
		for(int i = 2 ;i < 10 ; i++) {
			add.next = new ReverseSingleNode.SingleNode();
			add.next.value = ""+i;
			add = add.next;
		}
		
		SingleNode itr = node;
		while(null != itr) {
			System.out.print(itr.value+" ");
			itr = itr.next;
		}
		SingleNode cur = reverseSingleTest(node);
		System.out.println();
		SingleNode itr2 = cur;
		while(null != itr2) {
			System.out.print(itr2.value +" ");
			itr2 = itr2.next;
		}
	}
	
	public static void testReverseDoubleNode() {
		DoubleNode node = new ReverseSingleNode.DoubleNode();
		node.value = "1";
		DoubleNode last = node;
		DoubleNode add = node;
		for (int i = 2; i < 10; i++) {
			add.next = new ReverseSingleNode.DoubleNode();
			add.next.value = ""+i;
			add.next.pre = last;
			last = add.next;
			add = add.next;
		}
		
		DoubleNode itr = node;
		for (int i = 1; i < 10; i++) {
			System.out.print(itr.value + " ");
			itr = itr.next;
		}
		System.out.println();
		DoubleNode res = reverseDoubleNode(node);
		DoubleNode itr2 = res;
		for (int i = 1; i < 10; i++) {
			System.out.print(itr2.value + " ");
			itr2 = itr2.next;
		}
		
	}
	
	static class SingleNode{
		String value;
		SingleNode next;
	}
	
	static class DoubleNode{
		String value;
		DoubleNode pre;
		DoubleNode next;
		@Override
		public String toString() {
			return "DoubleNode [value=" + value + ", pre=" + pre + ", next=" + next + "]";
		}
		
	}
	
	
	public static SingleNode reverseSingleNode(SingleNode head) {
		//记录当前节点
		SingleNode pre = null;
		SingleNode next = null;
		while(null != head) {
			//下个节点的指针
			next = head.next;
			//当前节点指向前一个节点
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	public static DoubleNode reverseDoubleNode(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while(null != head) {
			next = head.next;
			head.next = pre;
			head.pre = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	
	
	
	
	
	
	
	
	
	public static SingleNode reverseSingleTest(SingleNode head) {
		SingleNode pre = null;
		SingleNode next = null;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		
		return pre;
	}
	
	public static DoubleNode reveDoubleTest(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while(head != null) {
			next = head.next;
			next.pre = next;
			head.next = pre;
			pre = head;
			head = next;
		}
		
		return null;
	}
}
