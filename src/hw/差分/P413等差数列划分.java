 

package hw.差分;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/26
 **/
public class P413等差数列划分 {

    /*
    等差 + 计数
    至少3个相邻数相差都同  如1 2 3
    由[1,2,3]，得到差值序列为[1,1]，长度为2，等差子序列个数X=1；
    由[1,2,3,4]，得[1,1,1]，长度为3，子序列为[1,2,3],[2,3,4],[1,2,3,4]，等差子序列个数X=2+1=3；
    由[1,2,3,4,5]，得[1,1,1,1]，长度为4，等差子序列个数X=3+2+1=6；
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int res = 0;
        int width = nums[0] - nums[1];
        int n = nums.length;
        int count = 0;
        // 第三个数组才开始有
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i] == width) {
                count++;
            } else {
                width = nums[i - 1] - nums[i]; // 开始匹配后面新宽度的子数组
                count = 0;
            }
            res += count;
        }
        return res;
    }

}
