package hw.滑动窗口;

public class P718最长重复子数组 {

    /**
     * 短数组放上面，长数组从左到右重合
     * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/wu-li-jie-fa-by-stg-2/
     * 滑动窗口
     */
    public int findLength(int[] A, int[] B) {
        return A.length <= B.length ? findMax(A, B) : findMax(B, A);
    }

    private int findMax(int[] A, int[] B) {
        int shortLen = A.length;
        int longLen = B.length;
        int res = 0;
        /*
        A:           |*|*|*|*|
        B: |*|*|*|*|*|*|
                 ↓
        A:       |*|*|*|*|
        B: |*|*|*|*|*|*|
        */
        for (int len = 1; len < shortLen; len++) {
            // 上面短数组每次比较都是0，下面数组是从倒数第几个开始比较  len 长度
            res = Math.max(res, findMaxLenInArrays(A, B, 0, longLen - len, len));
        }
        /*
        A:     |*|*|*|*|
        B: |*|*|*|*|*|*|
                 ↓
        A: |*|*|*|*|
        B: |*|*|*|*|*|*|
        */
        for (int i = longLen - shortLen; i >= 0; i--) {
            // 上面短数组每次比较都是0，下面数组是从倒数第几个开始比较  shortLen 固定长度
            res = Math.max(res, findMaxLenInArrays(A, B, 0, i, shortLen));
        }
        /*
        A: |*|*|*|*|
        B:   |*|*|*|*|*|*|
                 ↓
        A: |*|*|*|*|
        B:       |*|*|*|*|*|*|
        */
        for (int len = shortLen - 1; len > 0; len--) {
            // 短数组比较后面几个，长数组比较都是从0开始
            res = Math.max(res, findMaxLenInArrays(A, B, shortLen - len, 0, len));
        }
        return res;
    }

    /**
     * A中下标i开始，B中下标j开始，长度为len子数组中，最长公共子数组(注意要连续)长度
     */
    private int findMaxLenInArrays(int[] A, int[] B, int i, int j, int len) {
        int count = 0;
        int res = 0;
        for (int k = 0; k < len; k++) {
            if (A[i + k] == B[j + k]) {
                count++;
            } else {
                // 进入到这个if判断体里面，说明当前 nums1[i+k]!=nums2[j+k],即之前的公共子数组不再连续，
                // 所以要记录最大值，同时将count置零
                res = Math.max(res, count);
                count = 0;
            }
        }
        return Math.max(res, count);
    }

    /**
     * DP解法
     * 逆序倒推
     * 1 2 3 1
     * 1 3 3 1
     */
    public int findLengthDP(int[] A, int[] B) {
        int aLen = A.length;
        int bLen = B.length;
        int[][] dp = new int[aLen + 1][bLen + 1];
        int ans = 0;
        for (int i = aLen - 1; i >= 0; i--) {
            for (int j = bLen - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] : 0;
                ans = Math.min(ans, dp[i][j]);
            }
        }
        return ans;
    }

}
