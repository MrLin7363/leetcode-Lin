 

package hw.差分;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/25
 **/
public class P1109航班预定系统 {

    /*
    差分数组
    本题只涉及「区间修改 + 单点查询」，因此是一道「差分」的模板题。

    「差分」可以看做是求「前缀和」的逆向过程。

    对于一个「将区间 [l, r][l,r] 整体增加一个值 v操作，我们可以对差分数组 c 的影响看成两部分：

    对 c[l] += v ：由于差分是前缀和的逆向过程，这个操作对于将来的查询而言，带来的影响是对于所有的下标大于等于 ll 的位置都增加了值 vv；
    对 c[r + 1] -= v : 由于我们期望只对 [l, r][l,r] 产生影响，因此需要对下标大于 r 的位置进行减值操作，从而抵消“影响”。
    对于最后的构造答案，可看做是对每个下标做“单点查询”操作，只需要对差分数组求前缀和即可。
     */
    // 62+82
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] changes = new int[n+1];
        for (int[] booking : bookings) {
            int seats = booking[2];
            int begin = booking[0]-1;
            int end = booking[1]-1;
            changes[begin]+=seats;
            changes[end+1]-=seats;
        }
        // changes数组多了一位，要重新定义一个
        int[] ans = new int[n];
        ans[0]=changes[0];
        for (int i = 1; i < n; i++) {
            ans[i]=changes[i]+ans[i-1];
        }
        return ans;
    }


    /*
    暴力法 37 +6
     */
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] changes = new int[n];
        for (int[] booking : bookings) {
            int seats = booking[2];
            int begin = booking[0]-1;
            int end = booking[1]-1;
            for (int i = begin; i <= end; i++) {
                changes[i]+=seats;
            }
        }
        return changes;
    }

}
