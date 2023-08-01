package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/15
  *@Describe:
 */

public class P4寻找两个正序数组的中位数 {
    /*
    二分查找 O log(min(m,n))  90+70
    A贡献的元素数量 + B贡献的元素数量 = 要求的元素数量
    curA + curB = (A.length + b.length +1)/2)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length=nums1.length+nums2.length;
        //选择长度较小的那个数组进行查找
        if (nums1.length>nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }
        // 边界问题判断
        if (nums1.length==0){
            if (nums2.length%2!=0){
                return nums2[length/2];
            }else{
                 return (nums2[length/2-1] + nums2[length/2])/2.0;
            }
        }
        // 初始化二分查找的边界
        int Ledge=0,Redge=nums1.length;
        int cur1=0,cur2=0; // 两个数组贡献的元素数量,也是下标
        double result=0;
        while (Ledge<=Redge){
            cur1=Ledge+(Redge-Ledge)/2;
            cur2=(length+1)/2-cur1;
            //计算出L1，R1，L2，R2的值
            double L1= cur1==0?Integer.MIN_VALUE : nums1[cur1-1];
            double R1= cur1==nums1.length?Integer.MAX_VALUE:nums1[cur1];
            double L2= cur2==0?Integer.MIN_VALUE : nums2[cur2-1];
            double R2= cur2==nums2.length ? Integer.MAX_VALUE : nums2[cur2];
            //二分查找，重新划定边界
            if (L1>R2){
                Redge=cur1-1;
            }else if (L2>R1){
                Ledge=cur1+1;
            }else{ // 找到中间界线
                //注意长度为奇数偶数的问题，奇数取中间的那个值，偶数则取两边的和的一半
                if (length%2!=0){
                    result=Math.max(L1,L2);
                }
                else{
                    result= (Math.max(L1,L2)+Math.min(R1,R2))/2.0;
                }
                break;
            }
        }
        return result;
    }

    /*
    归并两个数组取中位   99+34  O m+n
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int[] nums=new int[nums1.length+nums2.length];
        int i=0,j=0,x=0;
        while (i<nums1.length&&j<nums2.length){
            if (nums1[i]<=nums2[j]){
                nums[x++]=nums1[i++];
            }else{
                nums[x++]=nums2[j++];
            }
        }
        while (i<nums1.length){
            nums[x++]=nums1[i++];
        }
        while (j<nums2.length){
            nums[x++]=nums2[j++];
        }
        int n=nums.length;
        // 偶数
        if (n%2==0){
//            return (double)(nums[n/2]+nums[n/2-1])/2;
            return (nums[n/2]+nums[n/2-1])/2.0;
        }else{// 奇数
            return nums[n/2];
        }
    }

    public static void main(String[] args) {
        P4寻找两个正序数组的中位数 ss=new P4寻找两个正序数组的中位数();
        System.out.println(ss.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
    }
}
