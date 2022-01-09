package com.csg.leetcode.leet.node;

/**
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * 
 * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
 * 
 * @author zhangxu
 *
 */
public class MergeTwoList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		ListNode cur = list1;
		int index = 0;
		ListNode bNode = null;
		ListNode aNode = null;
		while (cur != null) {
			if (a == index+1) {
				aNode = cur;
			}
			if (b == index) {
				bNode = cur.next;
				cur.next = null;
				break;
			}
			cur = cur.next;
			index++;
		}

		aNode.next = list2;
		ListNode rootList2 = null;
		ListNode curList2 = list2;
		while (curList2 != null) {
			if (curList2.next == null) {
				rootList2 = curList2;
				break;
			}
			curList2 = curList2.next;
		}

		rootList2.next = bNode;

		return list1;
	}
	public static void main(String[] args) {
		ListNode node = new ListNode(0);
		node.next = new ListNode(1);
		node.next.next = new ListNode(2);
		node.next.next.next =new ListNode(3);
		node.next.next.next.next = new ListNode(4);
		//[0,1000000,1000001,1000002,1,2,3,4,5]
		ListNode node2 = new ListNode(1000000);
		node2.next = new ListNode(1000001);
		node2.next.next = new ListNode(1000002);
		
		ListNode nodeReturn = mergeInBetween(node,3,4,node2);
		while(nodeReturn!=null) {
			nodeReturn = nodeReturn.next;
		}
	}
	
}
