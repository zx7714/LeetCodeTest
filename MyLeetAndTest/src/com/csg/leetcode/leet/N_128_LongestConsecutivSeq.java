package com.csg.leetcode.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**最长连续序列
 * 
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * 
 * 示例 1：
 * 
 * 输入：nums = [100,4,200,1,3,2] 输出：4 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 示例 2：
 * 
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1] 输出：9
 * 
 * @author zhangxu
 *
 */
public class N_128_LongestConsecutivSeq {
	class Solution {
		class UnionFind {
			Map<Integer, Integer> parents;

			public UnionFind(int[] arr) {
				parents = new HashMap<>();
				for (int i : arr) {
					parents.put(i, i);
				}
			}

			public Integer find(int x) {
				if (!parents.containsKey(x))
					return null;
				int t = parents.get(x);
				if (x != t)
					parents.put(x, find(t));
				return parents.get(x);
			}

			public boolean union(int x, int y) {
				Integer rootX = find(x), rootY = find(y);
				if (rootX == null || rootY == null)
					return false;
				if (rootX.equals(rootY))
					return false;
				parents.put(rootX, rootY);
				return true;
			}
		}

		public int longestConsecutive(int[] nums) {
			if (nums.length == 0)
				return 0;
			UnionFind u = new UnionFind(nums);
			for (int num : nums) {
				u.union(num, num + 1);
			}
			int max = 1;
			for (int num : nums) {
				max = Math.max(max, u.find(num) - num + 1);
			}
			return max;
		}
	}

	int count = 0;

	class UnionSet {
		Map<Integer, Integer> parent;
		Map<Integer, Integer> length;

		public UnionSet(int nums[]) {
			parent = new LinkedHashMap<Integer, Integer>();
			length = new LinkedHashMap<Integer, Integer>();
			for (int num : nums) {
				parent.put(num, num);
				length.put(num, 1);
			}
		}

		public Integer findFather(int num) {
			if (!parent.containsKey(num)) {
				return null;
			}
			Queue<Integer> q = new LinkedList<>();
			while (num != parent.get(num)) {
				q.add(num);
				num = parent.get(num);
			}
			while (!q.isEmpty()) {
				parent.put(q.poll(), num);
			}
			return num;
		}

		public void union(int n1, int n2) {
			Integer f1 = findFather(n1);
			Integer f2 = findFather(n2);
			if (f1 == null || f2 == null) {
				return;
			}
			if (f1 == f2) {
				return;
			}
			parent.put(f1, f2);
			Integer i1 = length.get(f1);
			Integer i2 = length.get(f2);
			try {
				length.put(f2, i1 + i2);
				length.remove(f1);
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public int longestConsecutive(int[] nums) {
		UnionSet us = new UnionSet(nums);
		//重复数字不重复计算
		HashSet<Integer> single = new HashSet<>();
		for (int num : nums) {
			if (!single.contains(num)) {
				//num更num+1合并，判断有没有num+1
				us.union(num, num + 1);
				single.add(num);
			}
		}

		int max = 0;
		Map<Integer, Integer> map = us.length;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			max = Math.max(max, entry.getValue());
		}
		return max;
	}


	public static void main(String[] args) {
		int arrs[] = { 1 };
		int count = new N_128_LongestConsecutivSeq().longestConsecutive(arrs);
		System.out.println(count);
	}

//	 public int maxSolute(int[] arrs) {
//		 
//		 Arrays.sort(arrs);
//		 int max = 0;
//		 for(int i = 1 ;i< arrs.length-1 ; i++) {
//			 if(arr[i]-)
//		 }
//		 return max;
//	 }

	// 生成一个max - min长度的二进制数组，值减最小值表示放到哪个位置，类似基数排序
	public int longestConsecutvieSequenct(int[] arrs) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int num : arrs) {
			max = Math.max(num, max);
			min = Math.max(min, num);
		}

		int length = max - min + 1;

		byte[] bytes = new byte[length];
		for (int num : arrs) {
			bytes[num - min] = 1;
		}
		int current = 0;
		int result = 0;
		for (byte b : bytes) {
			if (b == 1) {
				current++;
			} else {
				current = 0;
			}
			result = Math.max(result, current);
		}
		return result;
	}
}
