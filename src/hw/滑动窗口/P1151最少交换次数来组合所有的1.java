 

package hw.滑动窗口;

import javax.swing.plaf.IconUIResource;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/8
 **/
public class P1151最少交换次数来组合所有的1 {

    /*
    转化：固定窗口中求最少的0，窗口大小为数组中所有的1
     */
    public static int minSwaps(int[] data) {
        int n = data.length;
        // 窗口大小
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (data[i] == 1) {
                size += 1;
            }
        }
        int left=0;
        int right=0;
        int ans=Integer.MAX_VALUE;
        int nums_zero=0;
        while (right<n){
            // 右边界
            if (data[right]==0){
                nums_zero++;
            }
            // 不符合条件，左指针收缩
            while (right-left+1>size){
                if (data[left]==0){
                    nums_zero--;
                }
                left++;
            }
            // 更新结果
            if (right-left+1==size){
                ans=Math.min(ans,nums_zero);
            }
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        minSwaps(new int[]{1,0,1,0,1});
    }

}
