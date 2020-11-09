package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/09 23:14
 * @Describe:
 * 二分法，三分法。 三分法最坏情况糟糕很多
 */
public class Guess_Number_Higher_or_Lower_374 {
    public int guessNumber(int n) {
        int low=1;
        int high=n;
        while (low<=high){
//            int mid=(low+high)/2; 会溢出
            int mid=low+(high-low)/2; // 防止溢出
            int res=guess(mid);
            if (res==0)
                return mid;
            else if (res<0){
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        return -1;
    }
    public int guessNumber3(int n) {
        int low=1;
        int high=n;
        while (low<=high){
//            int mid=(low+high)/2; 会溢出
            int mid1=low+(high-low)/3; // 防止溢出
            int mid2=high-(high-low)/3; // 防止溢出
            int res1=guess(mid1);
            int res2=guess(mid2);
            if (res1==0)
                return mid1;
            if (res2==0)
                return mid2;
            else if (res1<0){
                high=mid1-1;
            }else if (res2>0){
                low=mid2+1;
            }else {
                low=mid1+1;
                high=mid2-1;
            }
        }
        return -1;
    }
    /*
    三分法
     */
    private int  guess(int mid){
        return 0;
    }
}
