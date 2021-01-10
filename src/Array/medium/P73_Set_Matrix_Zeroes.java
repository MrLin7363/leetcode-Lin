package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

import java.util.HashSet;
import java.util.Set;

public class P73_Set_Matrix_Zeroes {
    /*
    O 1 空间  91 + 65
     第一列不能置0，会影响后面行的判断
     */
    public void setZeroes(int[][] matrix) {
        boolean isCol=false;
        int rows=matrix.length,cols=matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0]==0){
                isCol=true; // 第一列都要为0
            }
            // 水平扫面，注意这里第二列开始
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j]==0){
                    // 将首行和首列的元素标志为0
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        // 扫描整个矩阵把对应行首元素或列首元素为0 位置置0
        // 第一行和第一列不能置0否则影响剩下判断
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        // 判断第一行元素是否需要置0，因为本来已经需要置0的已经置0，只用判断第一个
        if (matrix[0][0]==0){
            for (int i = 0; i < cols; i++) {
                matrix[0][i]=0;
            }
        }
        // 判断第一列元素是否需要置0
        if (isCol){
            for (int i = 0; i < rows; i++) {
                matrix[i][0]=0;
            }
        }
    }
    /*
    hashSet 5+5
     */
    public void setZeroes2(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
