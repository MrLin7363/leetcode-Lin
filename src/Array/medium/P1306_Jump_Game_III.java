package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe:
 */

import java.util.ArrayDeque;
import java.util.Queue;

public class P1306_Jump_Game_III {
    /*
    DFS 采用访问数组形式  10 + 5
     */
    public boolean canReach2(int[] arr, int start) {
        int[] seen = new int[arr.length]; // 0
        return dfs2(arr, start, seen);
    }
    public boolean dfs2(int[] arr, int start, int[] seen) {
        if (start<0 || start>=arr.length || seen[start]==1) return false;
        seen[start]++;
        if (arr[start] == 0) return true;
        return dfs2(arr, start - arr[start], seen) || dfs2(arr, start + arr[start], seen);
    }
    /*
    BFS  每次加入前后跳跃的两个结点  7 + 5
     */
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue=new ArrayDeque<>();
        queue.offer(start);
        int[] seen=new int[arr.length];
        while (!queue.isEmpty()){
            int pos=queue.poll();
            if (arr[pos]==0){
                return true;
            }
            // 遍历两个元素
            for (int nextPos:new int[]{pos+arr[pos],pos-arr[pos]}){
                if (nextPos<0 || nextPos>=arr.length || seen[nextPos]==1){
                    continue;
                }
                seen[nextPos]++;
                queue.offer(nextPos);
            }
        }
        return false;
    }

}
