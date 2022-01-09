package com.csg.leetcode.algorithm.week06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 合并用户，给定一个用户， 有邮箱、email、用户名， 只要有一个属性相同，那就表示这是一个用户。求用户数。
 * 
 * @author zhangxu
 *
 */
public class AccountMerge {
	public static class Node<V> {
		V value;

		public Node(V value) {
			this.value = value;
		}
	}

	public static List<List<String>> solution(List<List<String>> users) {
		List<String> mmp;
		//排序
		for(int i = 0;i < users.size() ;i++) {
			mmp = new ArrayList<>(users.get(i).size());
			String s0 = users.get(i).get(0);
			mmp.add(s0);
			users.get(i).remove(0);
			Set<String> tmpSet = new HashSet<>(users.get(i));
			List<String> tmpList = new ArrayList<>(tmpSet);
			Collections.sort(tmpList);
			mmp.addAll(tmpList);
			users.set(i, mmp);
		}	
		
		UnionSet<List<String>> unionSet = new UnionSet<List<String>>(users);
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
		for(List<String> user : users) {
			for (int i = 1; i < user.size(); i++) {
				if(map.containsKey(user.get(i))) {
					unionSet.union(user,map.get(user.get(i)));
				}else {
					map.put(user.get(i), user);
				}
			}
		}	
		users.clear();
		Map<Node<List<String>>, List<String>> mapRes = unionSet.resultMap;
		for(Map.Entry<Node<List<String>>, List<String>> entrySet: mapRes.entrySet()) {
			users.add(entrySet.getValue());
		}
		return users;
	}
	public static class UnionSet<V> {
		Map<List<String>, Node<List<String>>> nodesMap;
		Map<Node<List<String>>, Node<List<String>>> parentMap;
		Map<Node<List<String>>, List<String>> resultMap;
		public UnionSet(List<List<String>> list) {
			nodesMap = new HashMap<>();
			parentMap = new HashMap<>();
			resultMap = new HashMap<>();
			for (List<String> v : list) {
				nodesMap.put(v, new Node<List<String>>(v));
				parentMap.put(nodesMap.get(v), nodesMap.get(v));
				resultMap.put(nodesMap.get(v), v);
			}
		}

		public Node<List<String>> findFather(Node<List<String>> cur) {
			if (!parentMap.containsKey(cur))
				return null;
			Stack<Node<List<String>>> path = new Stack<>();
			while (cur != parentMap.get(cur)) {
				cur = parentMap.get(cur);
				path.push(cur);
			}
			while (!path.isEmpty()) {
				parentMap.put(path.pop(), cur);
			}
			return cur;
		}
		public void union(V a, V b) {
			if (!nodesMap.containsKey(a) || !nodesMap.containsKey(b)) {
				return;
			}
			Node<List<String>> aFather = findFather(nodesMap.get(a));
			Node<List<String>> bFather = findFather(nodesMap.get(b));
			if (aFather != bFather) {
				List<String> aSize = resultMap.get(aFather);
				List<String> bSize = resultMap.get(bFather);
				Node<List<String>> big = aSize.size() > bSize.size() ? aFather : bFather;
				Node<List<String>> small = big == aFather ? bFather : aFather;
				parentMap.put(small, big);
				resultMap.put(big,combineList(bSize,aSize));
				resultMap.remove(small);
			}

		}
		private List<String> combineList(List<String> bSize, List<String> aSize) {
			List<String> res = new LinkedList<>();
			res.add(bSize.get(0));
			
			aSize.remove(0);
			//Collections.sort(aSize);
			bSize.remove(0);
			//Collections.sort(bSize);
			
			int aIndex = 0;
			int bIndex = 0;
			while(aIndex < aSize.size() && bIndex < bSize.size()) {
				int cmt = aSize.get(aIndex).compareTo(bSize.get(bIndex));
				if(cmt < 0){
					res.add(aSize.get(aIndex++));
				}else if(cmt == 0){
					res.add(aSize.get(aIndex++));
					bIndex++;
				}else {
					res.add(bSize.get(bIndex++));
				}
			}
			while(aIndex < aSize.size()) {
				res.add(aSize.get(aIndex++));
			}
			while(bIndex < bSize.size()){
				res.add(bSize.get(bIndex++));
			}
			return res;
		}

	}
	
	public static void main(String[] args) {
	List<List<String>> users = new ArrayList<>();
	List<String> user = new ArrayList<>();
	user.add("John");
	user.add("john00@mail.com");
	user.add("john_newyork@mail.com");
	user.add("johnsmith@mail.com");
	users.add(user);
	
	user = new ArrayList<>();
	user.add("John");
	user.add("johnnybravo@mail.com");
	users.add(user);
	
	user = new ArrayList<>();
	user.add("Mary");
	user.add("mary@mail.com");
	users.add(user);
	
	
	user = new ArrayList<>();
	user.add("John");
	user.add("johnsmith@mail.com");
	user.add("john_newyork@mail.com");
	users.add(user);
	solution(users);
	
}
}

/**
 * [
 *   ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'], 
 *   ["John", "johnnybravo@mail.com"],
 *   ["Mary", "mary@mail.com"]
 *   ["John", "johnsmith@mail.com", "john_newyork@mail.com"]
 * ]
 *
 */




