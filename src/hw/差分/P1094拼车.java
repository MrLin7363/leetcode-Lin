 

package hw.差分;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/24
 **/
public class P1094拼车 {

    /*
    必须接送的乘客数量；
    乘客的上车地点；
    以及乘客的下车地点。

    差分数组，是一种和前缀和相对的策略。这种策略是，令b[i]=a[i]-a[i-1]，
    即相邻两数的差。 在每一个点上记录变化数值， 因为有增 加 有减少 通过 求 和判断是否有超过指定容量的情况发生，超过则代表无法满足要求。
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int max = 1001;
        // 路段最长1000
        int[] changes = new int[max];
        for (int[] trip:trips){
            int passengers = trip[0];
            // 在trip[1]上车的人数
            changes[trip[1]]+=passengers;
            // 在trip[0]下车的人数
            changes[trip[2]]-=passengers;
        }
        int currTotal=0;
        // 差分回溯过程
        for (int i = 0; i < max; i++) {
            currTotal+=changes[i];
            if (currTotal>capacity){
                return false;
            }
        }
        return true;
    }

}
