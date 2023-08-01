package Array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/16 21:59
 * @Describe:
 */
public class P207课程表 {
    // 存储有向图，第一个list纪录节点，第二个list纪录该节点所指向的节点
    List<List<Integer>> edges;
    // 存储每个节点的入度
    int[] indeg;
    // 存储答案
    int[] result;
    // 答案下标
    int index = 0;
    /*
    BFS
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg=new int[numCourses];
        result = new int[numCourses];
        // 初始化有向图
        for (int[] info:prerequisites){
            // prerequisites 完成 info 1 前要完成 info 0
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }
        // 将入度为0的放入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i]==0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            // 队列首部取出节点
            int u = queue.poll();
            result[index++]=u;
            // 相邻节点
            for (int v:edges.get(u)){
                indeg[v]--;
                // 如果相邻节点度数为0，则入队列
                if (indeg[v]==0){
                    queue.offer(v);
                }
            }
        }
        if (index != numCourses) {
            return false;
        }
        return true;
    }

}
