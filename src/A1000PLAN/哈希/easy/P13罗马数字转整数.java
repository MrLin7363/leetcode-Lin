package A1000PLAN.哈希.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *desc:
 *@author lin
 *@since 2023/11/21
 **/
public class P13罗马数字转整数 {
    private Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /*
    推荐-官方做法
     */
    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = map.get(s.charAt(i));
            if (i + 1 < n && value < map.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }

    // 自己写的可以忽略
    public int romanToInt2(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I' && i + 1 < chars.length && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                res += map.get(chars[i + 1]) - 1;
                i++;
            } else if (chars[i] == 'X' && i + 1 < chars.length && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                res += map.get(chars[i + 1]) - 10;
                i++;
            } else if (chars[i] == 'C' && i + 1 < chars.length && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                res += map.get(chars[i + 1]) - 100;
                i++;
            } else {
                res += map.get(chars[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 不存在VX的形式，这个为XV
        // new P13罗马数字转整数().romanToInt2("VX");

        new P13罗马数字转整数().romanToInt("MCMXCIV");
        new P13罗马数字转整数().romanToInt("IX");
        new P13罗马数字转整数().romanToInt("IV");
    }
}

