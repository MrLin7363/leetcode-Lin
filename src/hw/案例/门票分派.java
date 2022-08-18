 

package hw.案例;

import java.util.Deque;
import java.util.LinkedList;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/27
 **/
public class 门票分派 {
    /**
     * 最晚什么时间去前台保证可以拿到票
     * <p>
     * 达到以下条件失败，取上一个节点
     * 1.若有多人同时到达但余票不足，则余票随机分配
     * 2.超过分派时间
     * distribute = [11,15], num = 2, arrive = [11,12,15,15,15]
     */

    /**
     * 该解法在每个派发时间点按门票数遍历，变量cur存储返回值、变量idx存储当前已经处理的到达同事的数组索引
     * <p>
     * l  如果派发时间点小于arrive[idx]，说明有余票，cur=最后派票的时间
     * <p>
     * l  否则，本轮票派完了，cur=最后那个同事到达前一小时（要比最后那个同事早来，否则面临3个人抢2张票无法保证可以拿到票）
     *
     * @param distribute
     * @param num
     * @param arrive
     * @return
     */
    public int ticketDistribute(int[] distribute, int num, int[] arrive) {
        int ans = 0;
        int index = 0;
        for (int i = 0; i < distribute.length; i++) {
            for (int j = 0; j < num; j++) {
                if (distribute[i] < arrive[index]) {
                    // 无人符合领票，等于发票的时间
                    ans = distribute[i];
                    break;
                }
                ans = arrive[index++] - 1;
            }
        }
        return ans;
    }

    /**
     * 该解法用一个栈state存储到前台的时间，栈底是最后一个到达的、栈顶是第一个到达的
     * <p>
     * leftThisRound表示本轮是否还有剩余票。
     * <p>
     * 在遍历每一轮派票时，用变量count统计本轮领走的票数：
     * <p>
     * 若还未领完，从栈顶弹出一个元素（先到达的先弹出），同时最后一个拿到票的时间记入到变量lastOne；直到栈为空或者count等于0。
     * <p>
     * 最后遍历完所有轮次后，只剩下两种情况：
     * <p>
     * l  hasLeft为真，说明票还未领完，返回最后一轮派发票的时间
     * <p>
     * l  hasLeft为假，说明票已领完，返回最后一个人的时间减一
     */
    public int lastTime(int[] distribute, int num, int[] arrive) {
        int lastOne = 0;
        int index = 0; // 派发到哪个人
        for (int i = 0; i < distribute.length; i++) {
            for (int j = 0; j < num; j++) {
                if (distribute[i] < arrive[index]) {
                    break;
                }
                lastOne = arrive[index++];
            }
        }
        // 票已经领完了，还有人未发到票
        if (index < arrive.length) {
            return lastOne - 1;
        }
        return  distribute[distribute.length - 1];
    }

    public static void main(String[] args) {
        门票分派 begin = new 门票分派();
        int[] distribute = new int[]{11, 20};
        int num = 2;
        int[] arrive = new int[]{11, 12, 15, 15, 15};
        System.out.print(begin.lastTime(distribute, num, arrive));
    }

}
