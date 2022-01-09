package com.csg.leetcode.algorithm.week01;

import com.csg.leetcode.algorithm.week01.ReverseSingleNode.SingleNode;

/**
 * 给定链表头，从中删除所有指定值的节点。
 * @author zhangxu
 *
 */
public class DeletNumFromLinked {
	
	
	public 	SingleNode deleteSingleNode(SingleNode head,String num){
		
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}

		SingleNode headIdx = head;
		SingleNode changeIdx = head.next;
		
		while(changeIdx != null ) {
			if(changeIdx.value.equals(num)){
				headIdx.next = changeIdx.next;
			}else {
				headIdx = changeIdx;
			}
			changeIdx = changeIdx.next;
		}
		return head;
	}
}
