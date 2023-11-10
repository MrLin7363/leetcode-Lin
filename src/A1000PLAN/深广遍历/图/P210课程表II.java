package A1000PLAN.深广遍历.图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *desc:
 *@author lin
 *@since 2023/11/8
 **/
public class P210课程表II {
    /*
    拓扑排序
     */
    private List<List<Integer>> edges; //邻接表

    private int[] indge; // 入度

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        indge = new int[numCourses];
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indge[info[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indge.length; i++) {
            if (indge[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            res[index++] = course;
            for (int i : edges.get(course)) {
                indge[i]--;
                if (indge[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        if (index != numCourses) {
            return new int[] {};
        }
        return res;
    }

    public static void main(String[] args) {
        new P210课程表II().findOrder(2,new int[][]{{0,1}});
    }
}
