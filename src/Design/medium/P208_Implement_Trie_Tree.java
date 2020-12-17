package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe:
 */

import Construct.TrieNode;

public class P208_Implement_Trie_Tree {
    //  7 + 6
    class Trie {
        private TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root=new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node=root;
            for (int i = 0; i < word.length(); i++) {
                char curChar=word.charAt(i);
                // 如果当层没有该字符
                if (!node.containsKey(curChar)){
                    node.put(curChar,new TrieNode());
                }
                // 进入下一层
                node=node.get(curChar);
            }
            // 该字符串设置结束
            node.setEnd();
        }

        /** Returns if the word is in the trie. */
        // 如果该字符串是其他字符串的前缀，返回false
        public boolean search(String word) {
            TrieNode node=searchPrefix(word);
            return node!=null&&node.isEnd(); // 有这个字符串，不仅仅是前缀
        }
        // 寻找字符串前缀
        private TrieNode searchPrefix(String word) {
            TrieNode node=root;
            for (int i = 0; i < word.length(); i++) {
                char curChar=word.charAt(i);
                if (node.containsKey(curChar)){
                    node=node.get(curChar); // 进入下一层
                }else {
                    return null; // 前面的字符没有，没必要要继续查找
                }
            }
            // 该字符串设置结束
            return node;
        }
        /** Returns if there is any word in the trie that starts with the given prefix. */
        // 寻找是否有前缀
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }
}
