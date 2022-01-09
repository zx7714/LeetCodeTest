package com.csg.leetcode.algorithm.week06;

import java.util.HashSet;

/**
 * .给定一个字符串str，只有'X'个'.'两种字符构成。 
 * 
 *	'X'表示墙，不能放灯，也不需要点亮
 *	'.'表示居民点，可以放灯，需要点亮
 *
 *	如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 *
 *	返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * @author zhangxu
 *
 */
public class Light {
	public static int light(String road) {
		if(null == road || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0,new HashSet<Integer>());
	}
	/**
	 * 
	 * @param charArray
	 * @param index 放灯的位置
	 * @param hashSet 已经放置过灯的位置
	 * @return
	 */
	private static int process(char[] road, int index, HashSet<Integer> lights) {
		//所有位置全部放满了
		if(index == road.length) {
			for(int i = 0;i < road.length;i++) {
				if(road[i] != 'X') {
					if(!lights.contains(i-1) &&
							!lights.contains(i) &&
							!lights.contains(i+1)) { // i-1位置没灯，i位置没灯，i+1位置没灯，当前位置没被照亮
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		}else {
			//当前位置不放灯
			int no = process(road, index+1, lights);
			//当前位置选择放灯
			int yes = Integer.MAX_VALUE;
			//为  . 才可以放灯
			if(road[index] != 'X') {
				lights.add(index);
				yes = process(road, index+1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}
	
	
	private static int light2(String road) {
		if(road == null || road.length() == 0) {
			return 0;
		}
		char[] str = road.toCharArray();
		
		int index = 0;
		int light = 0;
		
		while(index < road.length()) {
			if(str[index] == 'X') {
				index++;
			}else {
				light++;
				if(index + 1 == road.length()) {
					break;
				}else {
					if(str[index+1] == 'X') {
						index +=2;
					}else {
						index +=3;
					}
				}
			}
		}
		return light;
	}
	
	// for test
		public static String randomString(int len) {
			char[] res = new char[(int) (Math.random() * len) + 1];
			for (int i = 0; i < res.length; i++) {
				res[i] = Math.random() < 0.5 ? 'X' : '.';
			}
			return String.valueOf(res);
		}

		public static void main(String[] args) {
			int len = 20;
			int testTime = 100000;
			for (int i = 0; i < testTime; i++) {
				String test = randomString(len);
				int ans1 = light(test);
				int ans2 = light2(test);
				if (ans1 != ans2) {
					System.out.println("oops!");
				}
			}
			System.out.println("finish!");
		}
		
}
