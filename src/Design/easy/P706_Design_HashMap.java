package Design.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/18
  *@Describe:
 */

import java.util.LinkedList;
import java.util.List;

public class P706_Design_HashMap {
    /*
    数组+链表自定义键值  58 + 75
     */
    static class MyHashMap {
        private Bucket[] buckets;
        private int key_space;
        public MyHashMap() {
            key_space=2069;
            buckets=new Bucket[key_space];
            for (int i = 0; i < key_space; i++) {
                buckets[i]=new Bucket();
            }
        }
        private int hash(int key){
            return key % key_space;
        }
        /** value will always be non-negative. */
        public void put(int key, int value) {
            int pos=hash(key);
            buckets[pos].update(key,value);
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index=hash(key);
            return buckets[index].get(key);
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index=hash(key);
            buckets[index].remove(key);
        }
    }
    static class Bucket{
        List<Pair<Integer,Integer>> bucket;

        public Bucket() {
            this.bucket = new LinkedList<Pair<Integer, Integer>>();
        }
        public int get(int key) {
            for (Pair<Integer,Integer> pair:bucket){
                if (pair.key.equals(key)){
                    return pair.value;
                }
            }
            return -1;
        }
        public void update(Integer key, Integer value) {
            boolean found=false;
            for (Pair<Integer,Integer> pair:bucket){
                if (pair.key.equals(key)){
                    pair.value=value;
                    found=true;
                }
            }
            if (!found){
                bucket.add(new Pair<>(key,value));
            }
        }
        public void remove(Integer key) {
            for (Pair<Integer,Integer> pair:bucket){
                if (pair.key.equals(key)){
                    bucket.remove(pair);
                    break;
                }
            }
        }
    }
    // key == value
    static class Pair<K,V>{
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap hashMap=new MyHashMap();
        hashMap.put(73576,39348);
        System.out.println(hashMap.get(73576));
    }
}
