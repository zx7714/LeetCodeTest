package com.csg.leetcode.algorithm.week01;

public class ArrayQueue<T> {
	private int len;
	private int size = 0;
	private int head = 0;
	private int tail = 0;
	private T array[];
	public ArrayQueue(int len){
		this.len = len;
	}
	public void push(T value) throws Exception {
		if(size == len) {
			throw new Exception("放不下了，满了");
		}
		size++;
		array[size] = value;
		tail = changeTail(tail);
	}
	public T popFromHead() throws Exception {
		if(size == 0) {
			throw new Exception("没有了，拿不到了");
		}
		T cur = array[head];
		head ++ ;
		head = changHead(head);
		return cur;
		
	}
	
	private int changHead(int size2) {
		// TODO Auto-generated method stub
		return 0;
	}
	private int changeTail(int size) {
		
		return 0;
	}
	
}
