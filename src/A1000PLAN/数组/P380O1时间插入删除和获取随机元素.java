package A1000PLAN.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *desc:
 *@author lin
 *@since 2023/11/16
 **/
public class P380O1时间插入删除和获取随机元素 {
    /*
    变长数组(查找O1)+哈希表(插入删除O1)存储下标
     */
    class RandomizedSet {
        private List<Integer> list;

        private Map<Integer, Integer> map;

        private Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            int lastIndex = list.size() - 1;
            Integer last = list.get(lastIndex);
            list.set(index, last);
            list.remove(lastIndex);
            map.put(last, index);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
