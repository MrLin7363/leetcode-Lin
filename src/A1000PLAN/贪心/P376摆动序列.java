package A1000PLAN.贪心;

/**
 * desc: 摆动序列： 差值一正一负，或者只有一个差值； 1个元素没有差值不算摆动序列
 *
 * @author Lin
 * @since 2023/8/15
 **/
public class P376摆动序列 {
    /*
    贪心
    每次加入一个新元素时，用新的上升下降趋势与之前对比，如果出现了「峰」或「谷」，答案加一，并更新当前序列的上升下降趋势
    1.记录两次差值，比较两次差值的正负情况
    /或 2. 用个值int drection=1 -1 等判断正负
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 1;
        }
        int res = 0;
        int prediff = nums[1] - nums[0];
        res += prediff != 0 ? 2 : 1;

        for (int i = 2; i < n; i++) {
            int cntDiff = nums[i] - nums[i - 1];
            // prediff 加 = 是用于第一次是一条线如3,3,3,2,5 的情况 -> 3 2 5
            if ((prediff >= 0 && cntDiff < 0) || (prediff <= 0 && cntDiff > 0)) {
                res++;
                prediff = cntDiff;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new P376摆动序列().wiggleMaxLength(new int[] {3, 3, 3, 2, 5});
    }
}
