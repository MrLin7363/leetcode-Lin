package A1000PLAN.排序.二分.边界类;

/**
 * desc:
 *
 * @author
 * @since 2023/9/20
 **/
public class P287寻找重复数 {
    /*
    二分-单调性  类似 P274H指数
    如果 cnt[i]<=i 那么当前i是不重复的，在target左侧
    如果 cnt[i]>i  那么i是target位置或者target 右侧
    左闭右开，找左边界  也可以左闭右闭
     */
    public int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length;
        int ans = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }

            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
                ans = mid;
            }
        }
        return ans;
    }

    /*
    快慢指针找环，找到后slow回初始节点再同步移动，相遇即是环的起点  其数字都在 [1, n] 范围内
     */
    public int findDuplicate2(int[] nums) {
        // slow,fast是值value
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        // 1,3,4,2,2
        new P287寻找重复数().findDuplicate(new int[] {3, 1, 3, 4, 2}); // 下标=4的时候相遇
        new P287寻找重复数().findDuplicate(new int[] {1, 4, 6, 6, 6, 2, 3});
    }
}
