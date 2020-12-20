package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe:
 */

import Construct.TrieNodeByList;
import java.util.ArrayList;
import java.util.List;

public class P212_Word_Search_II {

    /*
    前缀树  24 + 36
     */
    char[][] board = null;
    ArrayList<String> result = new ArrayList<String>(); // 返回的结果
    boolean[][] visited; // 访问标志，下面不用，用了改变当前元素的值，再恢复的方法，少了空间

    public List<String> findWords(char[][] board, String[] words) {
        // 初始化字典树
        TrieNodeByList root=new TrieNodeByList();
        for (String word:words){
            TrieNodeByList node=root;
            for (char letter:word.toCharArray()){
                if (node.children.containsKey(letter)){
                    node=node.children.get(letter);
                }else {
                    TrieNodeByList newNode = new TrieNodeByList();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word=word;  // 结束标识为当前字符串
        }
        // 遍历二维数组每个元素
        this.board=board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果有前缀
                if (root.children.containsKey(board[i][j])){
                    backtracking(i,j,root.children.get(board[i][j]));
                }
            }
        }
        return this.result;
    }
    private void backtracking(int row, int col, TrieNodeByList current) {
        if (current==null) return;
        if (current.word!=null){ // 匹配到一个字符串
            this.result.add(current.word);
            current.word=null; // 当前字符串置为null
        }
        // 记下原先的值
        Character letter=this.board[row][col];
        // 标志访问过的意思
        this.board[row][col]='#';
        // 移动二维数组
        int[][] directions= { {-1,0},{0,1},{1,0},{0,-1} }; // 上 右 下 左  不一定按顺序也行
        for (int[] direction:directions) {
            int newi = row + direction[0], newj = col + direction[1]; // 移动位置
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) { // 判断位置是否在二维数组中
                if (current.children.containsKey(this.board[newi][newj])){
                    backtracking(newi,newj,current.children.get(this.board[newi][newj]));
                }
            }
        }
        // 恢复标志
        this.board[row][col]=letter;
        if (current.children.isEmpty()){
            current.children.remove(letter);
        }
    }


    public static void main(String[] args) {
        P212_Word_Search_II re=new P212_Word_Search_II();
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'} };
        System.out.println(re.findWords(board,new String[]{"hklf", "hf"} ));
    }

}
