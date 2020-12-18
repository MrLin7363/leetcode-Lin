package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe: 字典树
 */

public class TrieNode {
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
