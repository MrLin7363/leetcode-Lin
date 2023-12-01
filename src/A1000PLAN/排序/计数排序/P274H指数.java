package A1000PLAN.排序.计数排序;

import java.util.Arrays;

/**
 *desc:
 *@author lin
 *@since 2023/11/17
 **/
public class P274H指数 {
    /*
    推荐
    计数排序：数组 counter 用来记录当前引用次数的论文有几篇
    O n O n
    https://leetcode.cn/problems/h-index/solutions/869042/h-zhi-shu-by-leetcode-solution-fnhl/?envType=study-plan-v2&envId=top-interview-150
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 引用次数>论文总数的情况：
            if (citations[i] > n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }

        // 当前总引用的论文
        int totalCitations = 0;
        // 从引用次数最多开始遍历
        for (int i = n; i > 0; i--) {
            totalCitations += counter[i];
            // 引用论文数>当前论文数
            if (totalCitations >= i) {
                return i;
            }
        }
        return 0;
    }


    /*
 不用看 理解，非常规模板题
https://leetcode.cn/problems/h-index/solutions/869420/gong-shui-san-xie-li-yong-er-duan-xing-z-1jxw/?envType=study-plan-v2&envId=top-interview-150
二分排序  Onlogn O1 性能最佳
搜索合法往[)搜索，因为右边第一个[可能合法，所有收缩右边界，采用偶数偏右的方式
 */
    public int hIndex3(int[] citations) {
        int n = citations.length;
        int l = 0, r = n;
        while (l < r) {
            // 偶数偏右
            int mid = (l + r + 1) / 2;
            // 合法右区间搜索，配合偶数偏右，使右边界不断收缩
            if (check(citations, mid)) {
                // 要找的答案在 [mid,right] 区间内
                l = mid;
            } else {
                // 要找的答案在 [left,mid) 区间内
                r = mid - 1;
            }
        }
        return r;
    }

    // 满足条件，比如mid=3,有起码3个论文的引用次数>=3,那就去右区间[]找,因为求最大引用次数
    boolean check(int[] citations, int mid) {
        int ans = 0;
        for (int c : citations) {
            if (c >= mid) {
                ans++;
            }
        }
        return ans >= mid;
    }


    // 普通排序-性能最差 nlogn logn
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        // 找到了一篇被引用了至少 h+1次的论文
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        new P274H指数().hIndex3(new int[] {0, 1, 3, 5, 6});

    }
}
