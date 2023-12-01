package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/11/23
 **/
public class P66加一 {
    // 找出最长的后缀9 逆序，找到第一个不为9的元素，后面的设置为0
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
