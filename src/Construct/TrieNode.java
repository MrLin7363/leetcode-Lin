package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe: 数组 - 字典树 结点
 */

public class TrieNode {
    /*
    一个结点指向孩子结点的“指针”们用数组表示；
     */
    private TrieNode[] links;
    private final int R=26;
    private boolean isEnd; // 判断是否有当前字符串，而不只是前缀

    public TrieNode() {
        links = new TrieNode[R];
    }
    public boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    public TrieNode get(char ch) {
        return links[ch-'a'];
    }
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
    public TrieNode[] getLinks() {
        return links;
    }


}
