package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe: 贪心Greedy
 */

public class P55_Jump_Game {
    /*
    贪心 84+73
     */
    public boolean canJump(int[] nums) {
        int rightMost=0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置大于最远到达的位置
            if (i>rightMost){
                return false;
            }
            rightMost=Math.max(rightMost,i+nums[i]);
        }
        return true;
    }
}
