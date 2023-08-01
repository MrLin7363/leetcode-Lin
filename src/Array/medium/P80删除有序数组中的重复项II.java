package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe: 最多出现两次
 */

public class P80删除有序数组中的重复项II {
    /*
    双指针 7+5
     */
    public int removeDuplicates(int[] nums) {
        int i=1;// 第一个元素默认不重复，从第二个开始计算
        int count=1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j]==nums[j-1]){
                count++;
            }else{
                count=1;
            }
            if (count<=2){
                nums[i++]=nums[j];
            }
        }
        return i;
    }
    /*
    O n^2   7+5
     */
    public int removeDuplicates2(int[] nums) {
        int len=nums.length;
        int i=1,count=1;
        while (i<len){
            if (nums[i]==nums[i-1]){
                count++;
                if (count>2){
                    // 删除重复元素，逐位后移
                    for (int j = i+1; j <nums.length; j++) {
                        nums[j-1]=nums[j];
                    }
                    i--;
                    len--;
                }
            }else{
                count=1;
            }
            i++;
        }
        return len;
    }

}
