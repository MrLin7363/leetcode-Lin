package A1000PLAN.双指针;

/**
 * desc:0-1-2 常数空间的一趟算法
 *
 * @author c30021507
 * @since 2023/9/22
 **/
public class P75颜色分类 {
    /*
    2趟遍历-单指针-推荐：两次遍历，第一次交换0到前面，第二次交换1到前面
    */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
        for (int right = 0; right < n; right++) {
            if (nums[right] == 1) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
    /*
    双指针：同向移动， p0=p1=0记录0和1的最大位置  遇到0交换p0位置，p0p1++;遇到1交换p1的位置，p1++
     */
    public void sortColors2(int[] nums) {
        int p0 = 0;
        int p1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[p0];
                nums[p0] = nums[i];
                nums[i] = temp;
                // 注意这种情况，0交换的一定是1，那么1得重新放入最新的位置
                if (p0 < p1) {
                    temp = nums[p1];
                    nums[p1] = nums[i];
                    nums[i] = temp;
                }
                p0++;
                p1++;
            } else if (nums[i] == 1) {
                int temp = nums[p1];
                nums[p1] = nums[i];
                nums[i] = temp;
                p1++;
            }
        }
    }
    /*
    双指针反向向中间遍历， 100 + 71  记录p2和p0的位置
     */
    public void sortColors3(int[] nums) {
        int len = nums.length;
        int p0 = 0, p2 = len - 1;// 分别代表0和2的要交换的位置
        for (int i = 0; i < len; i++) {
            // 遇到2交换,可能交换到前面的是2，所以还要再判断
            while (nums[i] == 2 && i <= p2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }


    public static void main(String[] args) {
        new P75颜色分类().sortColors2(new int[] {2, 0, 2, 1, 1, 0});
    }
}
