 

package hw.案例;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/22
 **/
public class 船票预定系统 {

    public static void main(String[] args) {
        船票预定系统 sysyem = new 船票预定系统(new int[]{10, 1});
        System.out.println(sysyem.book(71, 0, 2));
        System.out.println(sysyem.book(73, 0, 10));
        System.out.println(sysyem.book(72, 0, 2));
        System.out.println(sysyem.query(72));
        System.out.println(sysyem.book(74, 0, 2));
        System.out.println(sysyem.cancel(73));
        System.out.println(sysyem.query(74));
        System.out.println(sysyem.query(72));
        System.out.println(sysyem.cancel(72));
        System.out.println(sysyem.book(75, 0, 3));
        System.out.println(sysyem.query(75));
        System.out.println(sysyem.cancel(75));
        System.out.println(sysyem.book(76, 0, 2));
        System.out.println(sysyem.book(77, 0, 2));
        System.out.println(sysyem.cancel(76));
        System.out.println(sysyem.book(78, 0, 3));
        System.out.println(sysyem.query(78));
    }

    // 余票如果用打点法 int[] 的形式那么，每次添加的时候都要遍历的话比较耗时，如果用队列票已经卖出去的话,就不在队列，缺点是每次取消后增加余票时要排序
    private List<List<Integer>> tickets = new ArrayList<>(); // 余票：编号是船舱号，里层编号是座位号，船仓号是连续的不需要map

    private List<List<Order>> waitQueue = new ArrayList<>(); // 等待队列：编号是船舱号

    private Map<Integer, Order> orders = new HashMap<>(); // 订单：编号是订单号

    public 船票预定系统(int[] cabins) {
        for (int i = 0; i < cabins.length; i++) {
            List<Integer> singleTickets = new ArrayList<>();
            for (int j = 0; j < cabins[i]; j++) {
                singleTickets.add(j);
            }
            tickets.add(singleTickets);
            waitQueue.add(i, new ArrayList<>());
        }
    }

    public boolean book(int id, int cabinId, int num) {
        // 队列不为空或者位置不够预定失败
        Order order = new Order(id, cabinId, num);
        orders.put(id, order);
        if (!waitQueue.get(cabinId).isEmpty() || tickets.get(cabinId).size() < num) {
            waitQueue.get(cabinId).add(order);
            return false;
        }
        List<Integer> bookTickets = getBookTickets(cabinId, num);
        order.siteIds = bookTickets;
        return true;
    }

    /*
     若该订单已预订成功，则成功取消订单、退票并返回 true

o    若该订单位于候补队列，则成功取消订单、离开候补队列并返回 true

o    若该订单不存在或者已取消，返回 false。

Ø  处理规则：当成功取消订单后（含从候补队列取消），按照「先进先出」持续处理候补队列队首的订单，若余票满足队首订单需求，则预订成功，并从候补队列移除；继续处理队首订单，直到余票无法满足要求或候补队列为空为止
     */
    public boolean cancel(int id) {
        if (!orders.containsKey(id)) {
            return false;
        }
        Order order = orders.get(id);
        // 候补
        if (order.siteIds.isEmpty()) {
            // remove需要重写 equals 或者 order再加个id的属性
            waitQueue.get(order.cabinId).remove(order);
        } else {
            tickets.get(order.cabinId).addAll(order.siteIds);
            tickets.get(order.cabinId).sort(Integer::compareTo);
        }
        // 清理等待队列
        boolean isClear = true;
        while (isClear) {
            isClear = clearWaitQueue(order.cabinId);
        }
        orders.remove(id);
        return true;
    }
    /*
    循环处理等待队列，给等待队列发票
     */
    private boolean clearWaitQueue(int cabinId) {
        List<Order> waitOrder = waitQueue.get(cabinId);
        if (waitOrder.isEmpty()) {
            return false;
        }
        List<Integer> leftTickets = tickets.get(cabinId);
        if (leftTickets.isEmpty()) {
            return false;
        }
        // remove 掉后每次等待队列第一个都是最新的等待队列
        Order order = waitOrder.get(0);
        if (order.num > leftTickets.size()) {
            return false;
        }
        List<Integer> bookTickets = getBookTickets(cabinId, order.num);
        order.siteIds = bookTickets;
        waitOrder.remove(order);
        return true;
    }

    /*
    若订单已预定成功，返回最小的座位编号
    若订单状态为候补/已取消/不存在，返回 -1
     */
    public int query(int id) {
        Order order = orders.get(id);
        if (order == null || order.siteIds.isEmpty()) {
            return -1;
        }
        return orders.get(id).siteIds.get(0);
    }

    /*
    获取此订单预定的票号
    座位连续优先，如 678 > 238
     */
    private List<Integer> getBookTickets(int cabineId, int num) {
        List<Integer> leftTickets = tickets.get(cabineId);
        List<Integer> bookTickets = new ArrayList<>();
        int count = 0;
        int before = -2;
        // 找连续的座位
        for (int i = 0; i < leftTickets.size(); i++) {
            if (before + 1 == leftTickets.get(i)) {
                bookTickets.add(leftTickets.get(i));
                count++;
            } else {
                count = 1;
                bookTickets.clear();
                bookTickets.add(leftTickets.get(i));
            }
            before = leftTickets.get(i);
            if (count == num) {
                // 找到连续的直接返回
                leftTickets.removeAll(bookTickets);
                return bookTickets;
            }
        }
        // 找不到连续的就是按顺序出票,注意要新建一个List否则下面的引用会删掉
        List<Integer> unLianXuTickets = new ArrayList<>(leftTickets.subList(0, num));
        leftTickets.removeAll(leftTickets.subList(0, num));
        return unLianXuTickets;
    }

    private class Order {

        private int id;

        private int cabinId; // 船舱号

        private int num;

        private List<Integer> siteIds = new ArrayList<>();

        public Order(int id, int cabinId, int num) {
            this.id = id;
            this.cabinId = cabinId;
            this.num = num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.cabinId, this.num, this.id);
        }

        // order 有了 id 属性默认
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Order order = (Order) obj;
            return this.cabinId == order.cabinId && this.num == order.num && this.id == order.id;
        }
    }

}
