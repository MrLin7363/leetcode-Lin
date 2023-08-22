package A1000PLAN.贪心;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/17
 **/
public class P621任务调度器 {
    /*
    思路：
    1.记录每个元素的个数，优先处理个数多的，使用堆记录
    2.按每轮处理几个元素，逐个遍历每个元素，直到堆为空
    数据结构：
    int[]记录元素个数
    如何优先处理个数多的 -> 按个数降序 -> 使用大顶堆记录 int[]  或者优先队列也可以
    每轮处理n个任务，如果没有不相同的任务，则也算一次空闲调度
     */
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        // 记录每个元素个数
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[tasks[i] - 'A']++;
        }

        int res = 0;
        // 按次数排序,这里大顶堆，int[25]次数最大
        Arrays.sort(count);
        // 堆顶不为0，说明还有任务没有执行完
        while (count[25] > 0) {
            for (int i = 0; i <= n; i++) {
                // 如果任务已经执行完，则退出，否则会执行到n可能会有多几次待命
                if (count[25] == 0) {
                    break;
                }
                //每次从堆顶取冷却时间为0的任务执行
                //然后再贪心地取n个不相同的任务接在后面执行，如果没有n个不相同的任务，剩余时间就会处于待命状态
                if (25 - i >= 0 && count[25 - i] > 0) {
                    count[25 - i]--;
                }
                res++;
            }
            // 重新构建大顶堆
            Arrays.sort(count);
        }
        return res;
    }

    public static void main(String[] args) {
        new P621任务调度器().leastInterval(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2);
    }
}
