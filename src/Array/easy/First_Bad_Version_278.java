package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/10 19:12
 * @Describe:
 */
public class First_Bad_Version_278 {
    // 左闭右闭：找左边界
    public int firstBadVersion(int n) {
        int left=1,right=n;
        while (left<=right){
            int mid=left+(right-left)/2;
            if (!isBadVersion(mid)){
                // 往后找
                left=mid+1;
            }
            if (isBadVersion(mid)){
                // 往前找
                right=mid-1;
            }
        }
        return left;  // 最后返回 [ right , left ]   right是正确版本，left是第一个错误版本
    }
    // 二分法有很多模板,左闭右开
    public static int firstBadVersion2(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!isBadVersion(mid)){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left; // 结束条件是left==right所以这里返回哪一个都一样
    }
    public static boolean isBadVersion(int n) {
        return  true;
    }
}
