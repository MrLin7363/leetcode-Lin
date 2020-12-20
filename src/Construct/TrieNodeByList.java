package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe: 链表实现的 字典树 的 结点
 */

import java.util.HashMap;

public class TrieNodeByList {
    /*
   P212 用到 一个结点指向孩子结点的“指针”们用哈希表表示。
   */
    public HashMap<Character, TrieNodeByList> children ;
    public String word = null; // 判断是否还是叶子结点，如果有值，那么就是当前字符串

    public TrieNodeByList() {
        children = new HashMap<>();
    }


}
