package A1000PLAN.位运算;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/25
 **/
public class P89格雷编码 {
    public static void main(String[] args) {
        List<Integer> integers = new P89格雷编码().grayCode2(3);
        System.out.println(integers);
    }

    /*
    推荐记住
    官方做法：规律总结题  https://leetcode.cn/problems/gray-code/solutions/13637/gray-code-jing-xiang-fan-she-fa-by-jyd/
    每一种集合 前面+0算一份(list本身就是不用处理)，然后倒序，前面分别+1算另一半，具体看图
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<Integer>() {{
            add(0);
        }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            // 倒序在前面加+1   如二进制 10 在前面加1  就是 十进制的 2+4  10+100
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            // 左移，每次是 1,10,100,1000 相加就是相当于十进制前面+1
            head <<= 1;
        }
        return res;
    }

    /*
    自己写的回溯:正确，但超时了
     */
    public List<Integer> grayCode(int n) {
        int total = (int) Math.pow(2, n);
        return dfs(total, new ArrayList<>(), new boolean[total]);
    }

    private List<Integer> dfs(int total, List<Integer> list, boolean[] visited) {
        if (list.size() == total) {
            return list;
        }
        for (int i = 0; i < total; i++) {
            if (visited[i]) {
                continue;
            }
            // 相邻一位不同
            if (i >= 1 && !isOnlyOne(i, list.get(list.size() - 1))) {
                continue;
            }
            // 判断首尾的情况
            if (list.size() == total - 1 && !isOnlyOne(i, list.get(0))) {
                continue;
            }
            list.add(i);
            visited[i] = true;
            dfs(total, list, visited);
            if (list.size() == total) {
                break;
            }
            visited[i] = false;
            list.remove(list.size() - 1);
        }
        return list;
    }

    // 只有一位不同：超时
    private boolean isOnlyOne(int num1, int num2) {
        int num = num1 ^ num2;
        Integer.bitCount(num);
        // 如果异或后只剩1个1 就是符合条件
        // 找位1的个数，参考P191
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
            if (count > 1) {
                return false;
            }
        }
        return count == 1 ? true : false;
    }
}
