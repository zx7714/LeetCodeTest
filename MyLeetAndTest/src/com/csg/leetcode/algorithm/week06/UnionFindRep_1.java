package com.csg.leetcode.algorithm.week06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFindRep_1 {
	public static class Node<V> {
		V value;
		public Node(V value){
			this.value = value;
		}
	}
	
	public static class UnionSetRep_2s<V> {
		//放所有节点
		Map<V,Node<V>> nodeMap;
		//放子节点-父节点
		Map<Node<V>,Node<V>> parentMap;
		//放父节点的长度
		Map<Node<V>,Integer> sizeMap;
		
		public UnionSetRep_2s(List<V> list) {
			nodeMap = new HashMap<>();
			parentMap = new HashMap<>();
			sizeMap = new HashMap<>();
			
			for(V v : list) {
				nodeMap.put(v, new Node<V>(v));
				parentMap.put(nodeMap.get(v), nodeMap.get(v));
				sizeMap.put(nodeMap.get(v), 1);
			}
		}
		
		/**
		 * 查节点的父节点
		 * @param cur 传入节点
		 * @return 父节点
		 */
		public Node<V> findFather(Node<V> cur){
			if(null == cur) {
				return null;
			}
			
			//记录每次找最终父节点的路径；
			Stack<Node<V>> path = new Stack<>();
			//往上依次找父节点，如果不是最终的节点，周而复始
			while(cur != parentMap.get(cur)) {
				cur = parentMap.get(cur);
				path.push(cur);
			}
			//优化:把沿途所有的节点调整为直接指向最终的父节点
			while(!path.isEmpty()) {
				parentMap.put(path.pop(), cur);
			}
			
			return cur;
		}
		
		public boolean sameSite(V a,V b) {
			if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) {
				return false;
			}
			return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
		}
		
		
		public void union(V a,V b) {
			if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) {
				return;
			}
			Node<V> aFather = findFather(nodeMap.get(a));
			Node<V> bFather = findFather(nodeMap.get(b));
			
			if(aFather == bFather) {
				return;
			}
			int aSize = sizeMap.get(aFather);
			int bSize = sizeMap.get(bFather);
			Node<V> big = aSize > bSize ? aFather : bFather;
			Node<V> samll = big == bFather ? aFather : bFather;
			parentMap.put(samll, big);
			sizeMap.put(big, aSize + bSize);
			sizeMap.remove(samll);
		}
	}
	public static void main(String[] args) {
		String ars[] = {"a","b","c","d","e"};
		List<String> list = Arrays.asList(ars);
		UnionSetRep_2s<String> us = new UnionSetRep_2s<>(list);
		Node<String> a = us.nodeMap.get("a");
		Node<String> af = us.findFather(a);
		System.out.println("a's father : "+ af.value + " ,Obj : " + af);
		us.union("a", "b");
		
		af = us.findFather(af);
		
		System.out.println("a's father : "+ af.value + " ,Obj : " + af);
		Node<String> b = us.nodeMap.get("b");
		Node<String> bf = us.findFather(b);
		System.out.println("b's father : "+ bf.value + " ,Obj : " + bf);
		
		
		us.union("b", "c");
		bf = us.findFather(b);
		System.out.println("b's father : "+ bf.value + " ,Obj : " + bf);
		
		Node<String> c = us.nodeMap.get("c");
		Node<String> cf = us.findFather(c);
		System.out.println("c's father : "+ cf.value + " ,Obj : " + cf);
		
	}
}
