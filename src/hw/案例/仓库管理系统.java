package hw.案例;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计数法，容器统计法(size)
 */
public class 仓库管理系统 {

    /*
    物品已成功取出、物品未取仍在仓库中、物品被清空的存单数量
    第二个List记录 int date, int storageId, int storageType, int storageDays + dats  到期日
     */
    Map<Integer, List<Integer>> orders = new HashMap<>();

    List<Integer> prices = new ArrayList<>();

    List<Integer> nums = new ArrayList<>();

    int delay;

    int out; // 取出数量

    int giveUp; // 丢弃数量

    // 0 表示冷藏， 1 表示常温
    public 仓库管理系统(int coldStorageNum, int coldStoragePrice, int normalStorageNum, int normalStoragePrice, int delay) {
        prices.set(0, coldStoragePrice);
        prices.set(1, normalStoragePrice);
        nums.set(0, coldStorageNum);
        nums.set(1, normalStorageNum);
        this.delay = delay;
    }

    private int store(int date, int storageId, int storageType, int storageDays) {
        update(date);
        int type = storageType;
        if (nums.get(storageType) == 0) {
            if (storageType == 0) {
                return -1;
            } else if (nums.get(0) == 0) {
                return -1;
            } else {
                type = 0;
            }
        }
        List<Integer> order = new ArrayList<>();
        order.add(date);
        order.add(storageDays);
        order.add(type);
        order.add(date + storageDays + delay);
        orders.put(storageId, order);
        return storageDays * prices.get(type);
    }

    private int retrieve(int date, int storageId) {
        update(date);
        if (!orders.containsKey(storageId)) {
            return -1;
        }
        List<Integer> order = orders.get(storageId);
        nums.set(order.get(2), order.get(2) + 1);
        orders.remove(storageId);
        out++;
        // 未到期
        if (order.get(3) - delay < date) {
            return 0;
        }
        // 在延迟期内
        return prices.get(order.get(2)) * (date - order.get(3) - delay);
    }

    private int[] query(int date) {
        update(date);
        int[] res = new int[3];
        res[0] = out;
        res[1] = orders.size();
        res[2] = giveUp;
        return res;
    }

    /*
    更新时间，如果已经到期，清空物品
     */
    private void update(int date) {
        List<Integer> dius = new ArrayList<>();
        orders.forEach((storeId, list) -> {
            if (list.get(3) > date) {
                dius.add(storeId);
            }
        });
        for (int storeId : dius) {
            nums.set(orders.get(storeId).get(2), orders.get(storeId).get(2) + 1);
            orders.remove(storeId);
            giveUp++;
        }
    }

}
