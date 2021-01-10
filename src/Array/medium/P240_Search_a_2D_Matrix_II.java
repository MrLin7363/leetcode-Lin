package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

public class P240_Search_a_2D_Matrix_II {
    /*
    二分搜索单独的一行或者一列  12+5
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix==null||matrix.length==0){
            return false;
        }
        // 按对角线搜索，分别搜索当前行和列
        int minDiagonal=Math.min(matrix.length,matrix[0].length);
        for (int i = 0; i < minDiagonal; i++) {
            boolean verticalFound=binarySearch(matrix,target,i,true); // 水平搜索
            boolean horizontalFound=binarySearch(matrix,target,i,false);
            if (verticalFound||horizontalFound){
                return true;
            }
        }
        return false;
    }
    public boolean binarySearch(int[][] matrix,int target,int start,boolean isVertical){
        int low=start;
        int high=isVertical?matrix[0].length-1:matrix.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if (isVertical){ // 水平搜索
                if (matrix[start][mid] < target) {
                    low = mid + 1;
                } else if (matrix[start][mid] > target) {
                    high = mid - 1;
                } else {
                    return true;
                }
            }else{
                if (matrix[mid][start] < target) {
                    low = mid + 1;
                } else if (matrix[mid][start] > target) {
                    high = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    规律：从左下角开始查找 99+70
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row=matrix.length-1;
        int col=0;
        while (row>=0&&col<matrix[0].length){
            if (matrix[row][col]>target){ // 说明不在这一行
                row--;
            }else if (matrix[row][col]<target){ // 说明不在这一列
                col++;
            }else{
                return true;
            }
        }
        return false;
    }


}
