package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

public class P74_Search_a_2D_Matrix {
    /*
    二分法 O logn  100+43
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows=matrix.length,cols=matrix[0].length;
        int left=0,right=rows*cols-1;
        int pivotMidIndex,pivotElement;
        while (left<=right){
            pivotMidIndex=(right-left)/2+left;
            pivotElement=matrix[pivotMidIndex/cols][pivotMidIndex%cols];
            if (pivotElement==target){
                return true;
            }
            if (pivotElement>target){
                right=pivotMidIndex-1;
            }else{
                left=pivotMidIndex+1;
            }
        }
        return false;
    }
    /*
    MY： 每行最后一个判断，再判断这一行   100+92
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int rows=matrix.length,cols=matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][cols-1]>=target){
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j]==target){
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }
}
