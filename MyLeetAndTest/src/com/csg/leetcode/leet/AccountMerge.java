package com.csg.leetcode.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**账号合并
 * 
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称
 * (name)，其余元素是 emails 表示该帐户的邮箱地址。
 * 
 * 现在，我们想合并这些帐户。如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。
 * 
 * 合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。accounts 本身可以以任意顺序返回。
 * 
 * 例子 1:
 * 
 * Input: accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
 * ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]] Output: [["John",
 * 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'], ["John",
 * "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]] Explanation: 第一个和第三个 John
 * 是同一个人，因为他们有共同的电子邮件 "johnsmith@mail.com"。 第二个 John 和 Mary
 * 是不同的人，因为他们的电子邮件地址没有被其他帐户使用。
 * 我们可以以任何顺序返回这些列表，例如答案[['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]仍然会被接受。
 * 
 * @author zhangxu
 *
 */
public class AccountMerge {
	class Solution {
		public List<List<String>> accountsMerge(List<List<String>> accounts) {
			DSU dsu = new DSU();
			Map<String, String> emailToName = new HashMap<String, String>();
			Map<String, Integer> emailToID = new HashMap<String, Integer>();
			int id = 0;
			for (List<String> account : accounts) {
				String name = "";
				for (String email : account) {
					if (name == "") {
						name = email;
						continue;
					}
					emailToName.put(email, name);
					if (!emailToID.containsKey(email)) {
						emailToID.put(email, id++);
					}
					dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
				}
			}

			Map<Integer, List<String>> ans = new HashMap<Integer, List<String>>();
			for (String email : emailToName.keySet()) {
				int index = dsu.find(emailToID.get(email));
				ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
			}
			for (List<String> component : ans.values()) {
				Collections.sort(component);
				component.add(0, emailToName.get(component.get(0)));
			}
			return new ArrayList<List<String>>(ans.values());
		}
	}

	class DSU {
		int[] parent;

		public DSU() {
			parent = new int[10001];
			for (int i = 0; i <= 10000; ++i)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] != x)
				parent[x] = find(parent[x]);
			return parent[x];
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}
}
