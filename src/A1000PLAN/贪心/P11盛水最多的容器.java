package A1000PLAN.贪心;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/17
 **/
public class P11盛水最多的容器 {
    /*
    贪心，双指针， 比较两轴，较低的一侧不断向另一侧移动
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0;
        int j = n - 1;
        int sum = 0;
        while (i < j) {
            int curSum = (j - i) * Math.min(height[i], height[j]);
            sum = Math.max(curSum, sum);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        new P11盛水最多的容器().maxArea(new int[] {2, 3, 10, 5, 7, 8, 9});
    }
}
