package String.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/13
  *@Describe: 返回方案个数
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P52_N_Queens_II {
    /*
    位运算回溯-空间优化-三个整数记录是否已占用过列，左和右斜列
    100+92  全局变量
     */
    int result=0;
    public int totalNQueens2(int n) {
        int[] queens=new int[n];
        Arrays.fill(queens,-1);
        solve(queens,n,0,0,0,0);
        return result;
    }
    // 每次遍历确定一行的皇后
    public void solve(int[] queens,int n,int row,int columns,int diagonals1,int diagonals2){
        if (n==row){
            result++;
            return;
        }else{
            // 2^n -1 & !( ~(columns | diagonals1 | diagonals2) ) 公式后得到该行能放置皇后的位置，1为可以放置，0不可
            // 如11000010  第 2 ,7,8列可以放置皇后
            int availablePositions= ( (1<<n) -1) & ~( columns | diagonals1 | diagonals2 );
            while (availablePositions!=0){
                // 最低位的 1 的位置
                int position=availablePositions & (-availablePositions); // 00000010
                // 最低位的 1 置成 0
                availablePositions= availablePositions & (availablePositions-1); // 11000000
                int column = Integer.bitCount(position - 1);// 00000010 实际上是2 代表下标1 列
                queens[row]=column;
                // 移位要反向操作，这里表示1放置了，0默认没放置
                solve(queens,n,row+1,columns|position,
                        (diagonals1|position)<<1,(diagonals2|position)>>1);
                queens[row]=-1;
            }
        }
    }


    /*
    集合回溯 n皇后放在 N*N 棋盘  42+22
    递归变量叠加
     */
    Set<Integer> column=new HashSet<>(); // 每一列是否存皇后了
    Set<Integer> diagonalsLeft=new HashSet<>(); //斜线为从左上到右下
    Set<Integer> diagonalsRight=new HashSet<>();//斜线为从右上到左下
    public int totalNQueens(int n) {
        int[] queens=new int[n];
        Arrays.fill(queens,-1);
        return backtrack(queens,n,0);
    }
    // 一行行添加，每一行添加一个皇后
    public int backtrack(int[] queens,int n,int row){
        if (row==n){
            return 1;
        }else{
            int count=0;
            // 遍历每一列
            for (int i = 0; i < n; i++) {
                if (column.contains(i)){
                    continue;
                }
                // 左上角的一个元素，因为每一行的元素都是有效的，所以只用校验上一行的
                int diagonal1=row-i;
                if (diagonalsLeft.contains(diagonal1)){
                    continue;
                }
                int diagonal2=row+i;
                if (diagonalsRight.contains(diagonal2)){
                    continue;
                }
                // 当前row行i列位置可以放置皇后
                queens[row]=i;
                column.add(i);
                diagonalsLeft.add(diagonal1);
                diagonalsRight.add(diagonal2);
                count+=backtrack(queens,n,row+1);
                queens[row]=-1;
                column.remove(i);
                diagonalsLeft.remove(diagonal1);
                diagonalsRight.remove(diagonal2);
            }
            return count;
        }
    }
}
