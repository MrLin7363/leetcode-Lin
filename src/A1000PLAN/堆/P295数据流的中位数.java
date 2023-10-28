package A1000PLAN.堆;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * desc: 注意：这里的中位数指 有序序列 中的
 * https://leetcode.cn/problems/find-median-from-data-stream/solutions/961319/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/?envType=study-plan-v2&envId=top-100-liked
 * @author Lin
 * @since 2023/10/28
 **/
public class P295数据流的中位数 {
    /*
    双优先队列   左大顶堆，降序，<=中位数； 右小顶堆，升序,>=中位数
    队列都为空的情况优先添加左堆
     */
    static class MedianFinder {
        private Queue<Integer> leftQueue;

        private Queue<Integer> rightQueue;

        public MedianFinder() {
            leftQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            rightQueue = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (leftQueue.size() == rightQueue.size()) {
                if (leftQueue.isEmpty() || rightQueue.peek() >= num) {
                    leftQueue.add(num);
                } else {
                    leftQueue.add(rightQueue.poll());
                    rightQueue.add(num);
                }
            } else {
                // size不等的情况一定是左边多一个
                if (leftQueue.peek() <= num) {
                    rightQueue.add(num);
                } else {
                    rightQueue.add(leftQueue.poll());
                    leftQueue.add(num);
                }
            }
        }

        public double findMedian() {
            if (leftQueue.size() == rightQueue.size()) {
                return (leftQueue.peek() + rightQueue.peek()) / 2.0;
            } else {
                return leftQueue.peek();
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
    }
}
