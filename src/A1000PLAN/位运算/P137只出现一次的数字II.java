package A1000PLAN.位运算;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P137只出现一次的数字II {
    /*
    位运算：  哈希表存也可以太简单不展示
    代码：https://leetcode.cn/problems/single-number-ii/solutions/746993/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/?envType=study-plan-v2&envId=top-interview-150
    原理：https://leetcode.cn/problems/single-number-ii/solutions/8944/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/?envType=study-plan-v2&envId=top-interview-150
    出现3次的数，1的位置总和一定是3的倍数，其实就是3
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                // 注意：这里不能左移，是因为左移高位会抛弃，右移直到所有的1都能和1做与运算
                total += (num >> i) & 1;
            }
            // 当前位置是剩下那个数的1的位置，填充1
            if (total % 3 != 0) {
                ans |= (1 << i);
                // ans ^= (1 << i); 都行
            }
        }
        return ans;
    }
}
