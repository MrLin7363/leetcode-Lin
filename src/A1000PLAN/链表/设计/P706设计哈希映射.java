package A1000PLAN.链表.设计;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * desc: hashMap  P705的进阶版
 *
 * @author 
 * @since 2023/10/16
 **/
public class P706设计哈希映射 {
    /*
    数组+链表自定义键值  58 + 75
     */
    class MyHashMap {
        private class Bucket {
            private LinkedList<Pair> bucketList;

            public Bucket() {
                bucketList = new LinkedList<>();
            }

            public void update(int key, int value) {
                Iterator<Pair> iterator = bucketList.iterator();
                boolean found = false;
                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    if (pair.key == key) {
                        pair.val = value;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    bucketList.add(new Pair(key, value));
                }
            }

            public void remove(int key) {
                Iterator<Pair> iterator = bucketList.iterator();
                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    if (pair.key == key) {
                        iterator.remove();
                        break;
                    }
                }
            }

            public int get(int key) {
                Iterator<Pair> iterator = bucketList.iterator();
                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    if (pair.key == key) {
                        return pair.val;
                    }
                }
                return -1;
            }
        }

        private class Pair {
            private Integer key;

            private Integer val;

            public Pair(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private int BASE = 769;

        private Bucket[] buckets;

        public MyHashMap() {
            buckets = new Bucket[BASE];
            for (int i = 0; i < BASE; i++) {
                buckets[i] = new Bucket();
            }
        }

        public void put(int key, int value) {
            buckets[hash(key)].update(key, value);
        }

        public int get(int key) {
            return buckets[hash(key)].get(key);
        }

        public void remove(int key) {
            buckets[hash(key)].remove(key);
        }

        private int hash(int key) {
            return key % BASE;
        }
    }
}
