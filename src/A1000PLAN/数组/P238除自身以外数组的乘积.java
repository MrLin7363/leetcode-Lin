package A1000PLAN.数组;

/**
 * desc: On +O1 不使用除法
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P238除自身以外数组的乘积 {
    /* 符合题意推荐
    2.左右乘积列表 On+O1
    利用返回的结果数组节省空间
    */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // 下标i左侧的所有元素乘积 [0,i)
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // 计算结果时同时计算右侧所有元素乘积
        int right = nums[n - 1];
        res[n - 1] = res[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }

    /*
    1.左右乘积列表 On+On
    类似接雨水的双指针 / 前缀和(积)
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // 下标i左侧的所有元素乘积 [0,i)
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        // 下标i右侧的所有元素乘积 (i,n-1]
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    public static void main(String[] args) {
        new P238除自身以外数组的乘积().productExceptSelf(new int[] {1, 2, 3, 4});
    }
}