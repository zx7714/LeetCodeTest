package com.csg.leetcode.algorithm.week06.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.csg.leetcode.algorithm.week06.UnionFind.Node;
import com.csg.leetcode.algorithm.week06.UnionFind.UnionSet;

public class UnionFind {
	public static class Node<V>{
		V v;
		public Node(V v){
			this.v = v;
		}
	}
	
	public static class UnionSet<V>{
		Map<V,Node<V>> nodesMap;
		Map<Node<V>,Node<V>> parentMap; // key node ， value parent
		Map<Node<V>,Integer> sizeMap;
		
		public UnionSet(List<V> list){
			nodesMap = new HashMap<V,Node<V>>();
			parentMap = new HashMap<Node<V>,Node<V>>();
			sizeMap = new HashMap<>();
			for(V v : list){
				nodesMap.put(v, new Node<V>(v));
				parentMap.put(nodesMap.get(v), nodesMap.get(v));
				sizeMap.put(nodesMap.get(v), 1);
			}
		}
		
		public Node<V> findFather(Node<V> node){
			if(!parentMap.containsKey(node)){
				return null;
			}
			Stack<Node<V>> s = new Stack<>();
			
			while(node != parentMap.get(node)){
				node = parentMap.get(node);
				s.push(node);
			}
			while(!s.isEmpty()){
				parentMap.put(s.pop(), node);
			}
			return node;
		
		}
		
		public boolean isSameSit(V cur1,V cur2){
			if(!nodesMap.containsKey(cur2) || !nodesMap.containsKey(cur1)){
				return false;
			}
			return findFather(nodesMap.get(cur1)) == findFather(nodesMap.get(cur2));
			
		}
		
		public void union(V a,V b){
			if(!nodesMap.containsKey(a) || !nodesMap.containsKey(b)){
				return;
			}
			
			Node<V> f1 = findFather(nodesMap.get(a));
			Node<V> f2 = findFather(nodesMap.get(b));
		
			if(f1 != f2){
				int aSize = sizeMap.get(f1);
				int bSize = sizeMap.get(f2);
				Node<V> bigger = aSize > bSize ? f1 : f2;
				Node<V> smaller = bigger == f2 ? f1 : f2;
				parentMap.put(smaller, bigger);
				sizeMap.put(bigger, aSize + bSize);
				sizeMap.remove(smaller);
			}
			
		}
	}
	
	public static void main(String[] args) {
		String ars[] = {"a","b","c","d","e"};
		List<String> list = Arrays.asList(ars);
		UnionSet<String> us = new UnionSet<>(list);
		Node<String> a = us.nodesMap.get("a");
		Node<String> af = us.findFather(a);
		System.out.println("a's father : "+ af.v + " ,Obj : " + af);
		us.union("a", "b");
		
		af = us.findFather(af);
		
		System.out.println("a's father : "+ af.v + " ,Obj : " + af);
		Node<String> b = us.nodesMap.get("b");
		Node<String> bf = us.findFather(b);
		System.out.println("b's father : "+ bf.v + " ,Obj : " + bf);
		
		
		us.union("b", "c");
		bf = us.findFather(b);
		System.out.println("b's father : "+ bf.v + " ,Obj : " + bf);
		
		Node<String> c = us.nodesMap.get("c");
		Node<String> cf = us.findFather(c);
		System.out.println("c's father : "+ cf.v + " ,Obj : " + cf);
		
	}
}
