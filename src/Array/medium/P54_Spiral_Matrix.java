package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

import java.util.ArrayList;
import java.util.List;

public class P54_Spiral_Matrix {
    /*
    由外向内遍历  100+9
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return order;
        }
        int top=0,bottom=matrix.length-1,left=0,right=matrix[0].length-1;
        while (left<=right&&top<=bottom){
            // 添加上行
            for (int col = left; col <= right; col++) {
                order.add(matrix[top][col]);
            }
            // 添加右列
            for (int row = top+1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            //添加下行和左列，如果相同在上面已经添加
            if (left<right&&top<bottom){
                for (int col = right-1; col >left ; col--) {
                    order.add(matrix[bottom][col]);
                }
                /* 注意这里的row=bottom  情况如下，4作为最后添加的元素
                   1 2 3
                   4 2 1
                 */
                for (int row = bottom; row >top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
    /*
    访问二维矩阵+遇到路径换方向
    100+8
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return order;
        }
        int rows=matrix.length,cols=matrix[0].length;
        boolean[][] visited=new boolean[rows][cols]; // 访问矩阵默认 false
        int total=rows*cols; // 步数
        int[][] direction=new int[][]{{0,1},{1,0},{0,-1},{-1,0}}; // 右下左上
        int directionIndex=0; // 方向
        int row=0,col=0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][col]);
            visited[row][col]=true;
            int nextRow=row+direction[directionIndex][0],nextCol=col+direction[directionIndex][1];
            // 遇到边界或者访问过的，转换方向
            if (nextRow<0||nextRow>=rows||nextCol<0||nextCol>=cols||visited[nextRow][nextCol]){
                directionIndex=(directionIndex+1)%4;
            }
            row+=direction[directionIndex][0];
            col+=direction[directionIndex][1];
        }
        return order;
    }
}
