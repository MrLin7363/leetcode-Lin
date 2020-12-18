package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/18
  *@Describe: 字典树
 */

import Construct.TrieNode;

public class P211_Design_Add_Search_Words_Data_Structure {

    /*
    注意 dictionary.addWord("a");
        dictionary.addWord("a");   这个只添加了一层，就是一个元素 a
     */
    static class WordDictionary {
        private TrieNode root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root=new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node=root;
            for (int i = 0; i < word.length(); i++) {
                char curChar=word.charAt(i);
                if (!node.containsKey(curChar)){
                    node.put(curChar,new TrieNode());
                }
                node=node.get(curChar);
            }
            node.setEnd();
        }

        public boolean search(String word) {
            TrieNode node=searchPrefix(word,this.root);
            return node!=null&&node.isEnd(); // 有这个字符串，不仅仅是前缀
        }
        // 54 + 41
        private TrieNode searchPrefix(String word,TrieNode root) {
            TrieNode node=root;
            for (int i = 0; i < word.length(); i++) {
                char curChar=word.charAt(i);
                if (curChar=='.'){ // 递归判断所有不为空的孩子结点
                    // 递归判断字符串，字符串减去前面的 . 进入下一层结点   .是匹配任何字符，空不行
                    for(int j = 0;j < 26; j++){
                        if (node.getLinks()[j]!=null){
                            // 返回不匹配为null，匹配的为当前结点node.getLinks()[j] , 不能node=会改变 node下一次for遍历就不一样了
                            TrieNode res=searchPrefix(word.substring(i+1),node.getLinks()[j]);
                            if (res!=null && res.isEnd()) return res;
                        }
                    }
                    // 下一层没有结点，不匹配 .
                    return null;
                }else{
                    if (node.containsKey(curChar)){
                        node=node.get(curChar);
                    }else{
                        return null;
                    }
                }
            }
                    return node;
        }

        //  54 + 49
        private boolean searchPrefixByboolean(String word,TrieNode root) {
            TrieNode node=root;
            for (int i = 0; i < word.length(); i++) {
                char curChar=word.charAt(i);
                if (curChar=='.'){ // 递归判断所有不为空的孩子结点
                    // 递归判断字符串，字符串减去前面的 . 进入下一层结点   .是匹配任何字符，空不行
                    for(int j = 0;j < 26; j++){
                        if (node.getLinks()[j]!=null){
                            // 返回不匹配为null，匹配的为当前结点node.getLinks()[j] , 不能node=会改变 node下一次for遍历就不一样了
                            if(searchPrefixByboolean(word.substring(i+1),node.getLinks()[j]))
                                return true;
                        }
                    }
                    // 下一层没有结点，不匹配 .
                    return false;
                }else{
                    if (node.containsKey(curChar)){
                        node=node.get(curChar);
                    }else{
                        return false;
                    }
                }
            }
            return node.isEnd();
        }
    }

    public static void main(String[] args) {
        WordDictionary dictionary=new WordDictionary();
        dictionary.addWord("add");
        dictionary.addWord("adds");
        dictionary.addWord("adder");
        dictionary.addWord("a");
        System.out.println(dictionary.search(".a"));
        System.out.println(dictionary.search("add."));
//        System.out.println(dictionary.search(".a"));
//        System.out.println(dictionary.search("a.."));
    }
}
