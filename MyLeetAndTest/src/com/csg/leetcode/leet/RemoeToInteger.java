package com.csg.leetcode.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转为整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
	字符          数值
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
	例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
	
	通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
	
	I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
	C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
	给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
	
	示例 1:
	
	输入: "III"
	输出: 3
	示例 2:
	
	输入: "IV"
	输出: 4
	示例 3:
	
	输入: "IX"
	输出: 9
	示例 4:
	
	输入: "LVIII"
	输出: 58
	解释: L = 50, V= 5, III = 3.
	示例 5:
	
	输入: "MCMXCIV"
	输出: 1994
	解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * @author ZhangXu
 *
 */
public class RemoeToInteger {
	public static void main(String args[]){
		String s = "DCXXI";
		int result = new RemoeToInteger().remoeToIntegerMy(s);
		System.out.println(result);
	}
	public int remoeToIntegerMy(String s){
		char[] str = s.toCharArray();
		int result = 0;
		for(int i = 0;i<str.length;i++){
			if("M".equals(String.valueOf(str[i]))){
				result+=1000;
				continue;
			}
			if("D".equals(String.valueOf(str[i]))){
				result+=500;
				continue;
			}
			if("C".equals(String.valueOf(str[i]))){
				if(i+1<str.length){
					if("D".equals(String.valueOf(str[i+1]))){						
						result+=400;
						i++;
					}else if("M".equals(String.valueOf(str[i+1]))){
						result+=900;
						i++;
					}else{
						result+=100;
					}
					
				}else{
					result+=100;
				}
				continue;
			}
			if("L".equals(String.valueOf(str[i]))){
				result+=50;
				continue;
			}
			if("X".equals(String.valueOf(str[i]))){
				if(i+1<str.length){
					if("L".equals(String.valueOf(str[i+1]))){						
						result+=40;
						i++;
					}else if("C".equals(String.valueOf(str[i+1]))){
						result+=90;
						i++;
					}else{
						result+=10;
					}
					
				}else{
					result+=10;
				}
				continue;
			}
			if("V".equals(String.valueOf(str[i]))){
				result+=5;
			}
			if("I".equals(String.valueOf(str[i]))){
				if(i+1<str.length){
					if("V".equals(String.valueOf(str[i+1]))){						
						result+=4;
						i++;
					}else if("X".equals(String.valueOf(str[i+1]))){
						result+=9;
						i++;
					}else{
						result+=1;
					}
					
				}else{
					result+=1;
				}
				continue;
			}
		}
		return result;
		
	}
	public int remoeToIntegerLeetCode(String s){
		 Map<String, Integer> map = new HashMap<>();
	        map.put("I", 1);
	        map.put("IV", 4);
	        map.put("V", 5);
	        map.put("IX", 9);
	        map.put("X", 10);
	        map.put("XL", 40);
	        map.put("L", 50);
	        map.put("XC", 90);
	        map.put("C", 100);
	        map.put("CD", 400);
	        map.put("D", 500);
	        map.put("CM", 900);
	        map.put("M", 1000);
	        
	        int ans = 0;
	        for(int i = 0;i < s.length();) {
	            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {//字符串分割，取第i跟i+1位
	                ans += map.get(s.substring(i, i+2));
	                i += 2;//移动两位
	            } else {
	                ans += map.get(s.substring(i, i+1));
	                i ++;
	            }
	        }
	        return ans;
	}
}
