package A1000PLAN.哈希.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/26
 **/
public class P599两个列表的最小索引总和 {
    /*
    索引下标最小的可能有相同的多个
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            indexMap.put(list1[i], i);
        }
        List<String> res = new ArrayList<>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (indexMap.containsKey(list2[i])) {
                int j = indexMap.get(list2[i]);
                if (i + j < indexSum) {
                    res.clear();
                    res.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args) {
        new P599两个列表的最小索引总和().findRestaurant(new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"});
    }
}
