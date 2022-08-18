 

package hw.差分;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/26
 **/
public class P253会议室2 {

    /*
    给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突
    ，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
    参考拼车问题，此处可以优化changes数组为键值对Map，最后遍历map就行
     */
    public int minMeetingRooms(int[][] intervals) {
        int[] changes = new int[1000000];
        for (int[] interval : intervals) {
            int begin = interval[0];
            int end = interval[1];
            changes[begin] += 1;
            changes[end] -= 1;
        }
        int res = 0;
        int total =0;
        for (int i = 0; i < 1000000; i++) {
            total+=changes[i];
            res = Math.max(res, total);
        }
        return res;
    }

}
