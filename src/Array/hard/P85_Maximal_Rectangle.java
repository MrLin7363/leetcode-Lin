package Array.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/04 15:02
 * @Describe: 接着84题
 */
public class P85_Maximal_Rectangle {


    /*
   柱状图-单调栈-一次遍历版本  54+88
    */
    public int maximalRectangle(char[][] matrix) {
        int m=matrix.length;
        if (m==0){
            return 0;
        }
        int n=matrix[0].length;
        int[][] left = new int[m][n];
        // 当前元素 在该行左侧1的连续个数，宽的作用
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]=='1'){
                    left[i][j]=(j==0?0:left[i][j-1])+1;
                }
            }
        }
        // 直接求每一列的最大面积，不用一个元素一个元素求
        int res=0;
        for (int j = 0; j < n; j++) {
            int[] up = new int[m]; // 每一行确定宽，也就是上下边界
            int[] down = new int[m];
            Arrays.fill(down,m); // 下边界默认最下
            Deque<Integer> stack = new ArrayDeque<>();
            // 单调栈一次遍历法，在过程中确定下边界
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty()&&left[stack.peek()][j]>=left[i][j]){
                    down[stack.peek()]=i;
                    stack.pop();
                }
                up[i]=stack.isEmpty()?-1:stack.peek();
                stack.push(i);
            }
            int area=0;
            for (int i = 0; i < m; i++) {
                area=Math.max(area,left[i][j]*(down[i]-up[i]-1)); // (最下边界-最上边界)* 宽
            }
            res=Math.max(res,area);
        }
        return res;
    }
    /*
    柱状图暴力 26+65
     */
    public int maximalRectangle2(char[][] matrix) {
        int m=matrix.length;
        if (m==0){
            return 0;
        }
        int n=matrix[0].length;
        int[][] left = new int[m][n];
        // 当前元素 在该行左侧1的连续个数，宽的作用
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]=='1'){
                    left[i][j]=(j==0?0:left[i][j-1])+1;
                }
            }
        }
        int res=0;
        // 求每个元素的最大面积，每一个元素往上就是一个柱状图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]=='0'){
                    continue;
                }
                int width=left[i][j];
                int area=width; // 只算一行的情况
                // 往上算面积
                for (int k = i-1; k >=0 ; k--) {
                    width=Math.min(width,left[k][j]);
                    area=Math.max(area,(i-k+1)*width);
                }
                res=Math.max(res,area);
            }
        }
        return res;
    }

}