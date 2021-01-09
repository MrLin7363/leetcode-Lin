package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe:
 */

public class P45_Jump_Game_II {
    /*
    正向贪心 O n  64 + 5
     */
    public int jump(int[] nums) {
        int end=0;//上一次能跳的最远距离
        int maxPosition=0; // 当前能到达的最远位置
        int steps=0;
        for (int i = 0; i < nums.length-1; i++) {
            maxPosition=Math.max(i+nums[i],maxPosition);
            // 每一次能跳到的最远距离，在这基础上加跳跃的次数
            if (i==end){
                end=maxPosition;
                steps++;
            }
        }
        return steps;
    }
    /*
    反向贪心 On^2  11 + 37
     */
    public int jump2(int[] nums) {
        int position=nums.length-1;
        int step=0;
        // 从右往左查找最远到达最后一个结点的结点
        while (position>0){
            // 从左往右遍历查找最远的跳跃点
            for (int i = 0; i < nums.length; i++) {
                if (i+nums[i]>=position){
                    position=i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }
}
