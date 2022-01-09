package com.csg.leetcode.algorithm.week06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFind {
	public static class Node<V> {
		V value;

		public Node(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
		
	}

	public static class UnionSet<V> {
		Map<V, Node<V>> nodesMap;
		Map<Node<V>, Node<V>> parentMap;
		Map<Node<V>, Integer> sizeMap;

		// 构造并查集
		public UnionSet(List<V> list) {
			nodesMap = new HashMap<>();
			parentMap = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V v : list) {
				nodesMap.put(v, new Node<V>(v));
				parentMap.put(nodesMap.get(v), nodesMap.get(v));
				sizeMap.put(nodesMap.get(v), 1);
			}
		}

		/**
		 * 找到输入节点的父节点
		 * 
		 * @param cur 输入节点
		 * @return 父节点
		 */
		public Node<V> findFather(Node<V> cur) {
			if (!parentMap.containsKey(cur))
				return null;
			Stack<Node<V>> path = new Stack<>();
			// 从父节点map中找到父节点
			while (cur != parentMap.get(cur)) {
				cur = parentMap.get(cur);
				path.push(cur);
			}
			// 优化，把沿途所有以当前cur为父节点的节点，改为直接指向当前cur。
			while (!path.isEmpty()) {
				parentMap.put(path.pop(), cur);
			}
			return cur;
		}
		
		/**
		 *  HashSet<Integer> single = new HashSet<>();
		 for(int num : nums) {
			 if(single.contains(num)) {
				 single.add(num);
			 }else {
				 us.union(num, num+1);
			 }
		 }
		 */

		/**
		 * 给定两个值，判断有没有相同父节点
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public boolean isSameSite(V a, V b) {
			if (!nodesMap.containsKey(a) || !nodesMap.containsKey(b)) {
				return false;
			}
			return findFather(nodesMap.get(a)) == findFather(nodesMap.get(b));
		}

		/**
		 * 合并
		 * 
		 * @param a
		 * @param b
		 */
		public void union(V a, V b) {
			if (!nodesMap.containsKey(a) || !nodesMap.containsKey(b)) {
				return;
			}
			Node<V> aFather = findFather(nodesMap.get(a));
			Node<V> bFather = findFather(nodesMap.get(b));
			// 将节点长的父节点设置为两个节点父节点。
			if (aFather != bFather) {
				int aSize = sizeMap.get(aFather);
				int bSize = sizeMap.get(bFather);
				Node<V> big = aSize > bSize ? aFather : bFather;
				Node<V> small = big == aFather ? bFather : aFather;
				parentMap.put(small, big);
				sizeMap.put(big, aSize + bSize);
				sizeMap.remove(small);
			}

		}

	}
	
	public static void main(String[] args) {
		String ars[] = {"a","b","c","d","e"};
		List<String> list = Arrays.asList(ars);
		UnionSet<String> us = new UnionSet<>(list);
		Node<String> a = us.nodesMap.get("a");
		Node<String> af = us.findFather(a);
		System.out.println("a's father : "+ af.value + " ,Obj : " + af);
		us.union("a", "b");
		
		af = us.findFather(af);
		
		System.out.println("a's father : "+ af.value + " ,Obj : " + af);
		Node<String> b = us.nodesMap.get("b");
		Node<String> bf = us.findFather(b);
		System.out.println("b's father : "+ bf.value + " ,Obj : " + bf);
		
		
		us.union("b", "c");
		bf = us.findFather(b);
		System.out.println("b's father : "+ bf.value + " ,Obj : " + bf);
		
		Node<String> c = us.nodesMap.get("c");
		Node<String> cf = us.findFather(c);
		System.out.println("c's father : "+ cf.value + " ,Obj : " + cf);
		
	}
}
