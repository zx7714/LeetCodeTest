package com.csg.leetcode.leet.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。 
 * 左括号必须以正确的顺序闭合。
 * 
 * @author zhangxu
 *
 */
public class N_20_ValidParentheses {
	public boolean isValid(String s) {
		if (s.length() % 2 != 0) {
			return false;
		}
		Map<Character, Character> map = new HashMap<>();
		map.put(']', '[');
		map.put(')', '(');
		map.put('}', '{');
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char t = s.charAt(i);
			if (map.containsKey(t)) {
				if (stack.isEmpty() || stack.peek() != map.get(t)) {
					return false;
				}
				stack.pop();
			} else {
				stack.push(t);
			}
		}
		return stack.isEmpty();
	}

	public static boolean isValid2(String s) {
		if (s.length() % 2 != 0) {
			return false;
		}
		Map<Character, Character> map = new HashMap<>();
		map.put(']', '[');
		map.put('}', '{');
		map.put(')', '(');

		int[] arr = new int[s.length()];
		arr[0] = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < s.length(); i++) {
			int idx = i - arr[i - 1] - 1;
			if (map.containsValue(s.charAt(i)) || idx < 0 || s.charAt(idx) != map.get(s.charAt(i))) {
				arr[i] = 0;
				continue;
			}
			arr[i] = arr[i - 1] + 2;

			if (idx - 1 >= 0) {
				arr[i] += arr[idx - 1];
			}
			max = Math.max(arr[i], max);
		}

		return max == s.length();
	}

	public static void main(String[] args) {
		String str = "(){}{}{}";
		System.out.println(isValid2(str));
	}
}
