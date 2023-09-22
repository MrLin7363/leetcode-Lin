package A1000PLAN.双指针.判圈算法;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/20
 **/
public class P287寻找重复数 {
    /*
    快慢指针找环，找到后slow回初始节点再同步移动，相遇即是环的起点
     */
    public int findDuplicate(int[] nums) {
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
        new P287寻找重复数().findDuplicate(new int[] {3, 1, 3, 4, 2}); // 下标=4的时候相遇
        new P287寻找重复数().findDuplicate(new int[] {1, 4, 6, 6, 6, 2, 3});
    }
}
