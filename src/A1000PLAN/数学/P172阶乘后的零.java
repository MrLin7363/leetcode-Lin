package A1000PLAN.数学;

/**
 *desc:
 *@author lin
 *@since 2023/11/23
 **/
public class P172阶乘后的零 {
    /*
    首先末尾有多少个 0 ，只需要给当前数乘以一个 10 就可以加一个 0。
    2 * 5 构成了10 , 2的因子比5多
    判断每个累乘的数有多少个 5 的因子
    https://leetcode.cn/problems/factorial-trailing-zeroes/solutions/47030/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/?envType=study-plan-v2&envId=top-interview-150
     */
    public int trailingZeroes2(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    // 优化版
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
    public static void main(String[] args) {
        int n = 125;
        int res = 1;
        while (n > 0) {
            res *= n;
            n--;
        }
        System.out.println(res);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new P172阶乘后的零().trailingZeroes(125));
    }
}
