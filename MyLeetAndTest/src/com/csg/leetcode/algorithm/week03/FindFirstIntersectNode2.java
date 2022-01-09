package com.csg.leetcode.algorithm.week03;

public class FindFirstIntersectNode2 {
	
	public static Node findNodeIntersect(Node head1,Node head2) {
		if(head1 == null || head2 == null) {
			return null;
		}
		
		Node loop1 = findLoopNode(head1);
		Node loop2 = findLoopNode(head2);
		if(loop1 == null && loop2 == null) {
			return findNoLoopIntersectNode(head1, head2);
		}
		
		if(loop1 != null && null != loop2) {
			return findLoopIntersectNode(head1,loop1,head2,loop2);
		}
		return null;
	}
	


	private static Node findLoopIntersectNode(Node head1, Node loop1, Node head2, Node loop2) {
		//入环前就相交了
		if(loop1 == loop2) {
			Node cur1 = head1;
			Node cur2 = head2;
			int n = 0;
			while(cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while(cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head2 ? head1 : head2;
			n = Math.abs(n);
			while(n != 0) {
				cur1 = cur1.next;
				n--;
			}
			while(cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {
			Node cur1 = loop1.next;
			while(cur1 != loop1) {
				if(cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
		}
		
		return null;
	}



	public static Node findNoLoopIntersectNode(Node head1, Node head2){
		Node node1 = head1;
		Node node2 = head2;
		int n = 0;
		while(node1 != null) {
			n++;
			node1 = node1.next;
		}
		while(node2 != null) {
			n--;
			node2 = node2.next;
		}
		node1 = n > 0 ? head1 : head2;
		node2 = node1 == head2 ? head1 : head2;
		n = Math.abs(n);
		while(n != 0) {
			n--;
			node1 = node1.next;
		}
		while(node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}
	
	public static Node findLoopNode(Node head) {
		if(null == head || null == head.next || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;
		Node slow = head.next;
		while(fast != slow) {
			if(fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findNodeIntersect(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(findNodeIntersect(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findNodeIntersect(head1, head2).value);

	}
}
