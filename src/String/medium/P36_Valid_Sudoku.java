package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/12
  *@Describe: 数独
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P36_Valid_Sudoku {
    public static void main(String[] args) {
        P36_Valid_Sudoku s=new P36_Valid_Sudoku();
        System.out.println(s.isValidSudoku(new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        }));
    }
    /*
    官方 hashMap数组 84+38
     */
    public boolean isValidSudoku(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9]; // 值 - 出现次数
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int) num;
                    int box_index = (i / 3) * 3 + j / 3;
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);
                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1
                            || boxes[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
    hashmap+set 自己写的不用看  51+32
     */
    public boolean isValidSudoku2(char[][] board) {
        // 9个块hash   1~9 分别对应1~9个数字
        Map<Integer, Set<Character>> nineMap=new HashMap<>();
        // 行hash  0~8 行 分别对应1~9个数字
        Map<Integer, Set<Character>> rowMap=new HashMap<>();
        // 列hash 0~8 列 分别对应1~9个数字
        Map<Integer, Set<Character>> colMap=new HashMap<>();
        for (int i = 0; i < 9; i++) {
            rowMap.put(i,new HashSet<>());
            colMap.put(i,new HashSet<>());
            nineMap.put(i,new HashSet<>());
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char cc=board[i][j];
                if (cc>='0'&&cc<='9'){
                    if (rowMap.containsKey(i)&&rowMap.get(i).contains(cc)){
                        return false;
                    }
                    else if (colMap.containsKey(j)&&colMap.get(j).contains(cc)){
                        return false;
                    }
//                    int count=getNine(i,j);// 9宫格的个数 1~9
                    int count= (i / 3 ) * 3 + j / 3;;// 9宫格的个数 1~9
                    if (nineMap.containsKey(count)&&nineMap.get(count).contains(cc)){
                        return false;
                    }
                    rowMap.get(i).add(cc);
                    colMap.get(j).add(cc);
                    nineMap.get(count).add(cc);
                    /* 用初始化代替下面代码
                    if (!rowMap.containsKey(i)){
                        Set<Character> set=new HashSet<>();
                        set.add(cc);
                        rowMap.put(i,set);
                    }else{
                        rowMap.get(i).add(cc);
                    }
                    if (!colMap.containsKey(j)){
                        Set<Character> set=new HashSet<>();
                        set.add(cc);
                        colMap.put(j,set);
                    }else{
                        colMap.get(j).add(cc);
                    }
                    if (!nineMap.containsKey(count)){
                        Set<Character> set=new HashSet<>();
                        set.add(cc);
                        nineMap.put(count,set);
                    }else{
                        nineMap.get(count).add(cc);
                    }*/
                }
            }
        }
        return true;
    }
    // 这个可以省略为 (i / 3 ) * 3 + j / 3;
    public int getNine(int i , int j ){
        int row=i/3;// 0~2
        int col=j/3;// 0~2
        int count=1;
        switch (row){
            // 前三行
            case 0:
                count+=col;
                break;
            case 1:
                count+=col+3;
                break;
            case 2:
                count+=col+6;
                break;
        }
        return count;
    }
}
