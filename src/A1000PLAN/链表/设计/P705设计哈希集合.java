package A1000PLAN.链表.设计;

import Construct.BSTree;
import Construct.TreeNode;

import java.util.LinkedList;

// hashSet
public class P705设计哈希集合 {
    /*
    数组+单独链表法 61 + 71
     */
    class MyHashSet3 {
        private Bucket_LinkedList[] bucketArray;

        private int keyRange;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet3() {
            keyRange = 769; // 桶/数组的长度，质数 ，1W个元素时选这个好
            bucketArray = new Bucket_LinkedList[keyRange];
            // 初始化链表对象
            for (int i = 0; i < keyRange; i++) {
                bucketArray[i] = new Bucket_LinkedList();
            }
        }

        private int hash(int key) {
            return key % keyRange;
        }

        public void add(int key) {
            int pos = hash(key);
            bucketArray[pos].add(key);
        }

        public void remove(int key) {
            int pos = hash(key);
            bucketArray[pos].remove(key);
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int pos = hash(key);
            return bucketArray[pos].contains(key);
        }
    }

    class Bucket_LinkedList {
        private LinkedList<Integer> container;

        public Bucket_LinkedList() {
            container = new LinkedList<Integer>();
        }

        public void add(int key) {
            int index = container.indexOf(key);
            // 链表没有该元素才添加 ，因为是hashSet无重复元素
            if (index == -1) {
                container.addFirst(key);
            }
        }

        public void remove(int key) {
            int index = container.indexOf(key);
            if (index != -1) {
                container.remove(index);
            }
        }

        public boolean contains(int key) {
            return container.contains(key);
        }
    }

    public static void main(String[] args) {
               // MyHashSet hashSet=new MyHashSet();
               // hashSet.add(1);
               // hashSet.add(2);
               // hashSet.add(2);
               // hashSet.contains(2);
    }

    /*
   数组+二叉搜索树BST  62 + 71
    */
    class MyHashSet {
        private Bucket[] bucketArray;

        private int keyRange;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            keyRange = 769; // 桶/数组的长度，质数 ，1W个元素时选这个好
            bucketArray = new Bucket[keyRange];
            // 初始化链表对象
            for (int i = 0; i < keyRange; i++) {
                bucketArray[i] = new Bucket();
            }
        }

        private int hash(int key) {
            return key % keyRange;
        }

        public void add(int key) {
            int pos = hash(key);
            bucketArray[pos].insert(key);
        }

        public void remove(int key) {
            int pos = hash(key);
            bucketArray[pos].delete(key);
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int pos = hash(key);
            return bucketArray[pos].exists(key);
        }
    }

    class Bucket {
        private BSTree tree;

        public Bucket() {
            tree = new BSTree();
        }

        public void insert(Integer key) {
            this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
        }

        public void delete(Integer key) {
            this.tree.root = this.tree.deleteNode(this.tree.root, key);
        }

        public boolean exists(Integer key) {
            TreeNode node = this.tree.searchBST(this.tree.root, key);
            return (node != null);
        }
    }
}
