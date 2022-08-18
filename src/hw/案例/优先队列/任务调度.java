 

package hw.案例.优先队列;

import java.util.PriorityQueue;

/**
 * desc: 用两个优先队列解决
 *
 * @author junlin
 * @since 2022/1/5
 **/
public class 任务调度 {

    public static void main(String[] args) {
        taskScheduler(2, new int[][]{{1, 1}, {2, 1}, {3, 2}});
    }

    private static int taskScheduler(int resourcesNum, int[][] taskAttributes) {
        // 在此补充你的代码
        int result = 0;

        // 通过一个数组监控虚拟机执行过程，每个虚拟机执行的总时长,元素integer就是每一个虚拟机,这里默认从小到大排序
        PriorityQueue<Integer> vmExecuteStatus = new PriorityQueue<>(resourcesNum,
                (o1, o2) -> ((Integer) o1).intValue() - ((Integer) o2).intValue());

        // 对taskAttributes按照优先级进行排队
        PriorityQueue<int[]> taskPriorityQueue = new PriorityQueue<>(taskAttributes.length,
                (o1, o2) -> o1[1] < o2[1] ? -1 : (o1[1] > o2[1] ? 1 : o1[0] > o2[0] ? -1 : 1));

        // 将taskAttributes中的元素循环添加到taskPriorityQueue
        for (int i = 0; i < taskAttributes.length; i++) {
            taskPriorityQueue.add(taskAttributes[i]);
        }

        // 开始逐个遍历taskPriorityQueue中的元素
        while (!taskPriorityQueue.isEmpty()) {
            if (vmExecuteStatus.size() != resourcesNum) {
                vmExecuteStatus.add(taskPriorityQueue.poll()[0]);
            } else {
                vmExecuteStatus.add(vmExecuteStatus.poll() + taskPriorityQueue.poll()[0]);
            }
        }
        // vmExecuteStatus队尾的值就是最终结果
        while (!vmExecuteStatus.isEmpty()) {
            result = vmExecuteStatus.poll();
        }
        int res = (int) (result % (1e9 + 7));
        return res;
    }

        /*
    持续集成 CI 系统需要调度多台虚拟机资源 VM ，用于并发执行多个任务（每个任务有两个属性，执行时间T和优先级P），调度规则如下：

一个VM同一时间只能执行一个任务。

当VM不足时，优先级高的任务优先被执行，数字越小优先级越高；优先级相同的任务，执行时间长的先被执行。

当资源充足时，不同优先级的任务可以同时被执行。

现给定一次构建的N个任务，VM数量为M，请计算执行完所有任务的总时间。 结果需要取模 1e9+7（1000000007），如计算初始值为：1000000008，则返回 1。

输入：

第一行一个整数 M，表示空闲资源VM的数量，取值范围 [1,10000]。

第二行一个整数 N，表示该次构建的任务数量，取值范围[1,20000]。

接下来 N行，每行两个整数 T 和 P，分别表示任务的执行时间和优先级，取值范围：1 <= T <= 10^9， 1 <= P <= 10 。

输出：

一个整数，表示执行完所有任务的总时间。
     */

}
