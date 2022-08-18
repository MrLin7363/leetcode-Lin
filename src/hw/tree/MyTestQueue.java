 

package hw.tree;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/23
 **/
public class MyTestQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(1);
        queue.add(1);
        queue.add(6);
        queue.add(3);
        for (int num:queue){
            System.out.println(num);
        }
    }
}
