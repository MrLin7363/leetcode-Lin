package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe: 链表形式字典树结点 实现的 字典树  - 递归
 */

public class TrieByList {
    private TrieNodeByList root;
    public TrieByList(){
        root=new TrieNodeByList();
    }
    // Trie的操作
    private void delete(String str){
        delete(root,str,0);
    }
    // 这个是把叶子结点标识取消，相当于取消该字符串，但是该结点还在
    private TrieNodeByList delete(TrieNodeByList current,String str, int d){
        if(current==null) return null;
        if(d==str.length()){
            if(current.children.isEmpty()) return null; // 如果是叶子结点直接删除
            current.word = null;
            return current;
        }
        char c = str.charAt(d);
        TrieNodeByList next = delete(current.children.get(c),str,d+1);
        current.children.put(c,next);
        if(next==null&&current.word==null&&current.children.size()<=1)
            return null;
        return current;
    }
    private void insert(String str){
        insert(root,str,0);
    }
    //  递归的插入
    private TrieNodeByList insert(TrieNodeByList current,String str, int d){
        if(current==null)
            current = new TrieNodeByList();
        if(d==str.length()){
            current.word = str;
            return current;
        }
        char c = str.charAt(d);
        current.children.put(c, insert(current.children.get(c),str,d+1));
        return current;
    }

    public static void main(String[] args) {
        TrieByList trieByList=new TrieByList();
        trieByList.insert("word");
        trieByList.delete("word"); // 会把结点 ord 删除，置为null, 最后返回null，
        trieByList.insert("wo");
        System.out.println(trieByList);
        trieByList.delete("wo"); // 这个是把叶子结点标识取消，相当于取消该字符串，但是该结点还在
        trieByList.delete("wordddddd"); // 不会将 word 取消掉，要全部匹配才行
//        trieByList.insert("tre");
//        trieByList.delete("tre");
        System.out.println(trieByList);
    }
}
