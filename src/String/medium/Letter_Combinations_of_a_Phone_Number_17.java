package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/18
  *@Describe:[2-9] 电话号码字母交集
  1. 回溯算法，也是DFS
  2.BFS
*/

import java.util.*;

public class Letter_Combinations_of_a_Phone_Number_17 {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    List<String> output = new ArrayList<String>();

    // 回溯函数
    private void backtrack(String combination, String next_digits) {
        // 终止条件,这个是最后都加完了，才加进去，即是多叉树的子节点一层
        if (next_digits.length() == 0) {
            output.add(combination);
        } else { // 还有数字
            // 取下一位数字对应的字母
            String letters = phone.get(next_digits.substring(0, 1));
            // 遍历添加进当前合集
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                // 添加进当前合集，然后遍历。数字要去除第一位
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return output;
    }

    /*
    BFS算法----------------------------------
     */
    List<String> queue = new ArrayList<String>();
    public List<String> letterCombinations2(String digits) {
        if (digits.length()==0)
            return queue;
        queue.add("");
        // 遍历每一层
        for (int i = 0; i < digits.length(); i++) { // bfs的层数
            String letters = phone.get(digits.substring(i, i + 1)); // 每层要新匹配的字符串 如 abc
            int len=queue.size();
            for (int j = 0; j < len; j++) { // 遍历当前层
                String letter = queue.remove(j);// 逐个让当前层的节点(字母串)出队 ad ae af bd be bf cd ce cf
                for (int k = 0; k < letters.length(); k++) {
                    queue.add(letter + letters.charAt(k));
                }
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Letter_Combinations_of_a_Phone_Number_17 s=new Letter_Combinations_of_a_Phone_Number_17();
        s.letterCombinations2("23");
    }
}