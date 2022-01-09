package com.csg.leetcode.algorithm.week01;

public class DoubleNodeQueue {
	static class DoubleNode<T>{
		T value;
		DoubleNode<T> pre;
		DoubleNode<T> next;
		public DoubleNode(T t) {
			this.value = t;
		}
	}
	static class LinkedQueue<T>{
		DoubleNode<T> head = null;
		DoubleNode<T> tail = null;
		public void put(T value) {
			DoubleNode<T> node = new DoubleNode<T>(value);
			if(head == null) {
				head = node;
				tail = node;
			}else {
				node.next = head;
				head.pre = node;
				head = node;
			}
		}
		//后进先出，栈
		public T popFromHead(){
			if(null == head) {
				return null;
			}
			DoubleNode<T> cur = head;
			if(head == tail) {
				head = null;
				tail = null;
			}else {
				head = head.next;
				cur.next = null;
				head.pre = null;
			}
			return cur.value;
		}
		public T popFromTail(){
			if(null == head) {
				return null;
			}
			DoubleNode<T> cur = tail;
			if(tail == head) {
				head = null;
				tail = null;
			}else {
				tail = tail.pre;
				cur.pre = null;
				tail.next = null;
			}
			return cur.value;
		}
	}
}
