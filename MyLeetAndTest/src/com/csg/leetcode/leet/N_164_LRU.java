package com.csg.leetcode.leet;

import java.util.HashMap;
import java.util.Map;

public class N_164_LRU {
	class Node{
		private Node next;
		private Node pre;
		private Integer key;
		private Integer vaule;
		public Node(){}
		public Node(Integer key,Integer value){
			this.key = key;
			this.vaule = value;
		}
	}
	
	private int size;
	private int used;
	private Node head;
	
	private Node tail ;
	
	private Map<Integer,Node> cache;
		
	public N_164_LRU(int capacity) {
		this.size = capacity;
		this.used = 0;
		cache = new HashMap<Integer,Node>();
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.pre = head;
	}

	public void put(int key, int value) {
		if(used < size){
			if(!cache.containsKey(key)){
				used++;
			}
			cache.put(key, new Node(key,value));
			addToHead(cache.get(key));
			return;
		}
		Node pre = tail.pre;
		if(!cache.containsKey(key)){
			cache.remove(pre.key);
			cache.put(key, new Node(key,value));
			moveFromNodes(pre);
		}else{
			cache.get(key).vaule = value;
			moveFromNodes(cache.get(key));
		}
		addToHead(cache.get(key));
	}
	
	public int get(int key){
		if(null == cache.get(key)){
			return -1;
		}

		Node node = cache.get(key);
		moveFromNodes(node);
		addToHead(node);
		return node.vaule;
	}
	
	
	public void addToHead(Node node){
		
		Node headNext = head.next;
		head.next = node;
		node.pre = head;
		node.next = headNext;
		headNext.pre = node;
	}
	
	public void moveFromNodes(Node node){
		
		Node pre = node.pre;
		Node next = node.next;
		next.pre = pre;
		pre.next = next;
	}
	
	public static void main(String[] args) {
		//[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
		N_164_LRU lru = new N_164_LRU(2);
		System.out.println(lru.get(2));
		lru.put(2,6);
	
		//lru.put(2, 2);
		System.out.println(lru.get(1));
		lru.put(1, 5);
		//System.out.println(lru.get(2));
		lru.put(1, 2);
		System.out.println(lru.get(1));
		System.out.println(lru.get(2));
		System.out.println(lru.get(4));
	}
	
}
