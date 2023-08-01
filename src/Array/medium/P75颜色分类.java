package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/14
  *@Describe: 单指针/双指针
  不用深究的题
 */

public class P75颜色分类 {
    /*
    双指针反向向中间遍历， 100 + 71
     */
    public void sortColors(int[] nums) {
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

    /*
    双指针同向遍历，遇0同时右移，如果p0<p1 还要交换一次回来，遇1同时右移
    100 + 39
     */
    public void sortColors2(int[] nums) {
        int len=nums.length;
        int p0=0,p1=0;// 分别代表0和1的下一个要交换的位置
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) { // 说明交换过去的是1，1还要交换一次回来
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }
    }
    /*
    单指针：两次遍历，第一次交换0到前面，第二次交换1到前面
    100 + 8
     */
    public void sortColors3(int[] nums) {
        int len=nums.length;
        int pos=0;
        for (int i = 0; i < len; i++) {
            if (nums[i]==0){
                int temp=nums[pos];
                nums[pos]=nums[i];
                nums[i]=temp;
                pos++;
            }
        }
        for (int i = pos; i < len; i++) {
            if (nums[i]==1){
                int temp=nums[pos];
                nums[pos]=nums[i];
                nums[i]=temp;
                pos++;
            }
        }
    }



}
