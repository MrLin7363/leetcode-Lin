package DynamicProgramming.easy;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/12
  *@Describe:
 */

public class P53_Maximum_Subarray {
    /*
    DP 两个变量，一维数组再一次空间优化
     */
    public int maxSubArray3(int[] nums) {
        int n =nums.length;
        int pre=0;
        int maxAns=nums[0];
        for (int x:nums){
            pre=Math.max(pre,pre+x);// 逐个计算每一项的最大子序和
            maxAns=Math.max(maxAns,pre); // 如果该项最大子序和大于整体的最大自序和
        }
        return maxAns;
    }
    /*
    DP 一维数组 100+15
    dp(i)=max{ dp(i−1)+ai,ai }
     */
    public int maxSubArray2(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        dp[0]=nums[0];
        for (int i = 1; i < n; i++) {
            //dp[i]的最大子序和要么是自成一派最大，要么就是当前值加上前面i - 1个数的最大子序和
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
        }
        //遍历dp数组，求得dp数组中的最大值，就是该题的答案
        int res=Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    /*
    分治法：抄的
     */
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;// 以左区间为端点的最大子段和
            this.rSum = rSum;// 以右区间为端点的最大子段和
            this.iSum = iSum;// 区间所有数的和
            this.mSum = mSum;// 该区间的最大子段和
        }
    }
    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }
    public Status pushUp(Status l, Status r) {
        // 新子段的区间和等于左右区间的区间和之和
        int iSum = l.iSum + r.iSum;
        // 新子段的lSum等于左区间的lSum或者左区间的 区间和 加上右区间的lSum
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        // 新子段的rSum等于右区间的rSum或者右区间的 区间和 加上左区间的rSum
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        // 新子段的最大子段和，其子段有可能穿过左右区间，或左区间，或右区间
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;// 获得中间点mid
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);//mid+1,左右区间没有交集。
        return pushUp(lSub, rSub);
    }
}