package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/25 19:24
 * @Describe:
 */
public class MergeSortedArray_88 {
    /*
    双指针从后先前遍历，覆盖没事
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //设置两个指针
        int p1=m-1;
        int p2=n-1;
        //设置在nums1数组上最后面的位置指针
        int p=m+n-1;
        //逐一比较
        while(p2>=0&&p1>=0){
            nums1[p--]=(nums1[p1]>nums2[p2])?nums1[p1--]:nums2[p2--];
        }
        //把剩下的加上去
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy=new int[m];
        System.arraycopy(nums1,0,nums1_copy,0,m);
        //定义双指针
        int p1=0;
        int p2=0;
        //定义1数组的指针
        int p=0;
        //逐一比较大小
        while(p1<m&&p2<n){
            nums1[p++]=(nums1_copy[p1]<nums2[p2])?nums1_copy[p1++]:nums2[p2++];
        }
        //剩下的加进去
        while(p1<m){
            nums1[p++]=nums1_copy[p1++];
        }
        while(p2<n){
            nums1[p++]=nums2[p2++];
        }
    }
}