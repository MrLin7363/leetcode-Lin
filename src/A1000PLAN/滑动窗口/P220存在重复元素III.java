package A1000PLAN.滑动窗口;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * desc: hard
 *
 * @author Lin
 * @since 2023/8/10
 **/
public class P220存在重复元素III {
    /*
    一、滑动窗口
    通过indexDiff 设置滑动窗口 ，窗口内判断值差距

    找到一个数据结构维护滑动窗口内的元素，该数据结构需要满足以下操作：
    1.支持添加和删除指定元素的操作，否则我们无法维护滑动窗口； 有序集合

    2.内部元素有序，支持二分查找的操作，这样我们可以快速判断滑动窗口中是否存在元素满足条件，具体而言，对于元素 x，
    当我们希望判断滑动窗口中是否存在某个数 y 落在值区间 [x−t,x+t]中， 意思是值是否在区间内
    只需要判断滑动窗口中所有大于等于 x−t 的元素中的最小元素是否小于等于 x+t  即可
    x: 集合内一个数
    |x - num[i]| <=valueDiff
    ->    nums[i] - valueDiff  <= x <= valueDiff + nums[i]
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            // 大于这个值 且值最小的元素
            Long ceiling = set.ceiling((long) nums[i] - (long) valueDiff);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) valueDiff) {
                return true;
            }

            set.add((long) nums[i]);
            // 移动窗口左侧
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }

    /*
    https://leetcode.cn/problems/contains-duplicate-iii/solutions/726905/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-dlnv/
    桶 思路 1. 按value 桶划分，如下面是 0123|4567|89 10 11 间隔划分为桶
    2. 如果是同一个桶内，那么差值肯定小于value
    3. 如果是间隔桶，那么差值可能小于，需要判断
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        Map<Long, Long> map = new HashMap<>();
        long size = valueDiff + 1; //确保差值小于等于 t 的数能够落到一个桶中  如 [0,1,2,3]应该都在同一个桶中
        for (int i = 0; i < nums.length; i++) {
            long value = nums[i] * 1L;
            Long id = getId(value, size);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && value - map.get(id - 1) <= valueDiff) {
                return true;
            }
            if (map.containsKey(id + 1) && map.get(id + 1) - value <= valueDiff) {
                return true;
            }
            map.put(id, value);
            if (i >= indexDiff) {
                map.remove(getId(nums[i - indexDiff] * 1L, size));
            }
        }
        return false;
    }

    private Long getId(long numsValue, long size) {
        // 负数情况也要处理 如 [-4,-3,-2,-1] 在同一个区间  由于 0 号桶已经被使用了，我们还需要在此基础上进行 -1，相当于将负数部分的桶下标（idx）往左移
        return numsValue >= 0 ? numsValue / size : (numsValue + 1) / size - 1;
    }

    public static void main(String[] args) {
        // new P220存在重复元素III().containsNearbyAlmostDuplicate2(new int[] {1, 20, 45, 25}, 6, 5);
        new P220存在重复元素III().containsNearbyAlmostDuplicate2(new int[] {-3, 3, 6}, 2, 3);
    }
}
