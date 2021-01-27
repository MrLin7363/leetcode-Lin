package String.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/12
  *@Describe:
 */

import java.util.ArrayList;
import java.util.List;

public class P37_Sudoku_Solver {
    /*
    递归回溯 97+44
     */
    boolean[][] line=new boolean[9][9];
    boolean[][] column=new boolean[9][9];
    boolean[][][] block=new boolean[3][3][9];
    boolean isValid=false; // 最后结束的标志
    List<int[]> spaces=new ArrayList<>(); // 存空格的位置
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]=='.'){
                    spaces.add(new int[]{i,j});
                }else{
                    int digit=board[i][j]-'0'-1; // char 存0~9 + 48 -1 就是 48~57 转为int0~9
                    line[i][digit]=column[j][digit]=block[i/3][j/3][digit]=true;
                }
            }
        }
        dfs(board,0);
    }
    public void dfs(char[][] board,int pos){
        if (pos==spaces.size()){
            isValid=true; // 空格已经填完，说明数独已经解开
            return;
        }
        int[] space=spaces.get(pos);
        int i=space[0],j=space[1];
        // 1~9个数字 且未填完
        for (int digit=0;digit<9 && !isValid ; digit++){
            // 如果该位置可以填入数字不冲突
            if (!line[i][digit]&&!column[j][digit]&&!block[i/3][j/3][digit]){
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                // 填入数字
//                char s=(char)(digit+'0'+1);
                board[i][j]=(char)(digit+'0'+1);
                // 递归
                dfs(board,pos+1);
                // 回溯状态归位
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
   /* public static void main(String[] args) {
        P37_Sudoku_Solver s=new P37_Sudoku_Solver();
        s.solveSudoku(new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        });
    }*/
    public static void main(String[] args) {
        char digit='4';
//        System.out.println((char)(digit-'0'));
        System.out.println((char)(digit-'0'-1));
        System.out.println(digit-'0'-1);
        System.out.println(digit+'0'-1);
        System.out.println(digit+'0'+1);
        System.out.println(digit+'0');
    }
}
