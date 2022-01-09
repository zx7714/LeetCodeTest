package com.csg.leetcode.algorithm.week03;

import java.util.HashMap;
import java.util.Map;

/**
 * 单链表中含有一个随机节点，随机节点指向链表中的一个节点，拷贝这个链表
 * @author zhangxu
 *
 */
public class CopyNodeWithRandomNode {
	public static class Node{
		int value;
		Node next;
		Node random;
		public Node(int value) {
			this.value = value;
		}
	}
	
	
	public Node copyNode1(Node head) {
		Map<Node,Node> map = new HashMap<>();
		Node cur = head;
		while(cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		
		cur = head;
		while(cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		
		return map.get(head);
	}
	/**
	 * 新的节点作为老的节点next节点。找随机节点时老节点的随机节点的next节点就是copy出的新的节点
	 * @param head
	 * @return cur
	 */
	public Node copyNode2(Node head) {
		if(head == null) {
			return null;
		}
		
		Node cur = head;
		Node next = null;
		//copy出的节点放到旧链表中，作为next节点
		while(cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null;
		//copy random节点
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.random = cur.random == null ? null : cur.random.next;
			cur = next;
		}
		
		
		Node copy = head.next;
		cur = head;
		//copy出的链表与原链表分离
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next == null ? null : next.next;
			cur = next;
		}
		return copy;
		
	}
}
