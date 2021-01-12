package DynamicProgramming.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/11
  *@Describe:
 */

public class P152_Maximum_Product_Subarray {
    /*
    DP  两个一维数组
    同时维护最小值dp和最大值dp
    最小值DP是防止负数最小的时候，再遇到一个负数，然后转为真数后最大
     */
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int[] maxDp=new int[n];
        int[] minDp=new int[n];
        System.arraycopy(nums,0,maxDp,0,n);
        System.arraycopy(nums,0,minDp,0,n);
        for (int i = 1; i < n; i++) {
            // 先最小的*nums[i]看是否负负得正 minDp[i-1]*nums[i]
            maxDp[i]=Math.max(maxDp[i-1]*nums[i], Math.max(minDp[i-1]*nums[i],nums[i]) );
            // 先最大的*nums[i] 看是否*负数得到最小的 maxDp[i-1]*nums[i]
            minDp[i]=Math.min( minDp[i-1]*nums[i],Math.min(maxDp[i-1]*nums[i],nums[i]) );
        }
        int ans=maxDp[0];
        for (int i = 1; i < n; i++) {
            ans=Math.max(ans,maxDp[i]);
        }
        return ans;
    }
    /*
    DP 两个变量 93+18
     */
    public int maxProduct2(int[] nums) {
        int n=nums.length;
        int maxDp=nums[0],minDp=nums[0],ans=nums[0];
        for (int i = 1; i < n; i++) {
            int max=maxDp,min=minDp;
            maxDp=Math.max(max*nums[i],Math.max(min*nums[i],nums[i]));
            minDp=Math.min(min*nums[i],Math.min(max*nums[i],nums[i]));
            ans= Math.max(maxDp,ans);
        }
        return ans;
    }

}
