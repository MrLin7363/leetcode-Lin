package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe:
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class P380_Insert_Delete_GetRandom_O1 {
    /*
    数组 + hash   80 + 35
     */
    static class RandomizedSet {
        private List<Integer> list;
        private Map<Integer,Integer> dict; // 值 - 下标索引
        Random rand=new Random();
        public RandomizedSet() {
            dict=new HashMap<>();
            list=new ArrayList<>();
        }
        // 不含重复
        public boolean insert(int val) {
            if (dict.containsKey(val)){
                return false;
            }
            dict.put(val,list.size());
            list.add(list.size(),val); // 插入指定位置
            return true;
        }

        public boolean remove(int val) {
            if (!dict.containsKey(val)){
                return false;
            }
            int index=dict.get(val); // 要删除元素的下标
            int lastElementVal=list.get(list.size()-1);//最后一个元素值
            dict.put(lastElementVal,index); // 注意 put set 要在remove前，否则只有一个元素的时候remove了，又put 进去，因为 lastElement==val
            list.set(index,lastElementVal);
            list.remove(list.size()-1);
            dict.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size())); //  [0,list.size() )
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet=new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        System.out.println( randomizedSet.remove(1));
        System.out.println( randomizedSet.insert(1));

    }
}
