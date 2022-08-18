
package hw.拓扑排序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author junlin
 * @Since 2021/11/16
 **/
public class P210课程表2 {

    List<List<Integer>> edges; // 邻接表，通过节点的索引，能找到该节点的后续节点
    int[] indge; // 入度
    int[] result;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        indge = new int[numCourses];
        result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // [3,1],[3,2] 学习课程3，先学1,2 对于邻接表来说， 1->3,2->3
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indge[info[0]]++;
        }
        // 添加入度为0的节点
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < indge.length; i++) {
            if (indge[i] == 0) {
                queue.offer(i);
            }
        }
        // 此处的访问次序直接作为结果数组的index也行
        int index = 0;
        // 拓扑排序依次出度
        while (!queue.isEmpty()) {
            int u = queue.poll();
            // 访问该节点
            result[index++] = u;
            // 遍历该节点所有下一层节点
            // 如果下一层节点度数为0，加入队列
            for (int i : edges.get(u)) {
                indge[i]--;
                if (indge[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        if (index != numCourses) {
            return new int[]{0};
        }
        return result;
    }



}
