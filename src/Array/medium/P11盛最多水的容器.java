package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/8
  *@Describe:
  1. 短边缩进 91+ 9
  2. 暴力枚举
 */

public class P11盛最多水的容器 {
    public int maxArea(int[] height) {
        int left=0,right=height.length-1;
        int maxArea=0;
        while (left<right){
            int area = (right-left)*Math.min(height[left],height[right]);
            maxArea=Math.max(maxArea,area);
            // 短边内缩进
            if (height[left]>height[right]){
                right--;
            }else{
                left++;
            }
        }
        return maxArea;
    }
}
