 

package hw.案例.贪心;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/27
 **/
public class 任务规划 {
    /*
    贪心算法
    标准做法 ： set + map
     */
    public int setTask(int[] tasks, int[][] mutexPairs) {
        Map<Integer, List<Integer>> mutexMap = initMap(mutexPairs); // 互斥map
        Set<Integer> mutexSet = new HashSet<>(); // 已经访问过的数据
        int ans = 1;
        for (int i = 0; i < tasks.length; i++) {
            List<Integer> mutexList = mutexMap.get(tasks[i]);
            if (mutexList != null && mutexList.size() != 0) {
                for (int mutex : mutexList) {
                    if (mutexSet.contains(mutex)) {
                        ans++;
                        mutexSet.clear();
                        break;
                    }
                }
            }
            mutexSet.add(tasks[i]);
        }
        return ans;
    }

    private Map initMap(int[][] mutexPairs) {
        Map<Integer, List<Integer>> mutexMap = new HashMap<>();
        for (int[] mutex : mutexPairs) {
            mutexMap.putIfAbsent(mutex[0], new LinkedList<>());
            mutexMap.get(mutex[0]).add(mutex[1]);
            mutexMap.putIfAbsent(mutex[1], new LinkedList<>());
            mutexMap.get(mutex[1]).add(mutex[0]);
        }
        return mutexMap;
    }

    public static void main(String[] args) {
        任务规划 begin = new 任务规划();
        int[] tasks = new int[]{1, 3, 2, 4, 6, 5, 0};
        int[][] mutexPairs = new int[2][2];
        mutexPairs[0][0] = 1;
        mutexPairs[0][1] = 3;
        mutexPairs[1][0] = 4;
        mutexPairs[1][1] = 5;
        System.out.println(begin.setTask(tasks, mutexPairs));
    }

    /**
     * 任务列表 tasks 中有 N 个任务，任务编号 Ni​ 的值范围为 [0, N-1]。
     *
     * 由于存在资源竞争，某些任务间存在两两互斥关系，并记录在二维数组 mutexPairs 中，该二维数组元素为 [Ni​, Nj​]，其中 Ni​，Nj​ 为互斥的两个任务编号。
     *
     * 现在需要对任务列表 tasks 进行切割分组。要求：
     *
     * l  存在互斥关系的任务不能分在同一组
     *
     * l  单个任务也可以单独一组
     *
     * l  一个任务可能和多个任务互斥
     *
     * 请判断 最少 可以将任务列表 tasks 切割 成几组？（即：切割后的小组是原列表的 连续子数组）
     * 输入：tasks = [1,3,2,4,6,5,0], mutexPairs = [[1,3],[4,5]]
     *
     * 输出：3
     *
     * 解释：任务1，3 不能被分在同一组；4，5 不能被分在同一组，所以最终只能将任务列表分成3组。例如，[1],[3,2,4,6],[5,0] 为其中一种分法；[1],[3,2,4],[6,5,0] 为另一种分法。
     * 注意：切割后的小组是原列表的连续子数组。
     */

}
