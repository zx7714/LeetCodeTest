package com.csg.leetcode.leet.node;

/**
 * 对链表进行插入排序
 * 
 * @author zhangxu
 *
 */
public class InsertSortOfList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode insertionSortList(ListNode head) {
		while(head == null || head.next == null) {
			return head;
		}
		//记录子节点的直接父节点
//		Map<ListNode,ListNode> pre = new HashMap<>();
//		
//		ListNode curNode = head.next;
//		ListNode cur = null;
//		pre.put(curNode, head);
//		
//		while(curNode != null) {
//			ListNode preNode = pre.get(curNode);
//			while(preNode != null) {
//				if()
//			}
//			pre.get(wNode)
//			
//			curNode = curNode.next;
//		}
		
		return null;
	}
}
