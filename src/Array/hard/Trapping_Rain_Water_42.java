package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/8
  *@Describe:
  1.暴力 n^2   5+6
  2.DP   87+6
  3.栈
  4.双指针
 */


import java.util.Deque;
import java.util.LinkedList;

public class Trapping_Rain_Water_42 {

    public static void main(String[] args) {
        trap3(new int[]{4,3,2,3,5});
    }
    /*
    栈
     */
    public static int trap3(int[] height) {
        // 定义栈
        Deque<Integer> deque=new LinkedList<>();
        int size=height.length;
        int current=0;
        int ans=0;
        while (current<size){
            while (!deque.isEmpty() && height[current]>height[deque.peek()]){
                int top=deque.pop();// 栈顶元素出队，也就是凹点
                if (deque.isEmpty())
                    break;
                int distance=current-deque.peek()-1;
                int bound_height=Math.min(height[current],height[deque.peek()])-height[top];
                ans+=distance*bound_height;
            }
            // 下标存入，之前进入while循环的current也会push一次，所以上面要用while，因为ans+有时候为0
            deque.push(current++);
        }
        return ans;
    }

    /*
    DP
     */
    public int trap2(int[] height) {
        if (height==null || height.length==0)
            return 0;
        int ans=0;
        int size=height.length;
        int[] left_max=new int[size];
        int[] right_max=new int[size];
        // 先逐个确定每一个的左最高和右最高
        left_max[0]=height[0];
        for (int i = 1; i < size; i++) {
            left_max[i]=Math.max(left_max[i-1],height[i]);
        }
        right_max[size-1]=height[size-1];
        for (int i = size-2; i >=0 ; i--) {
            right_max[i]=Math.max(right_max[i+1],height[i]);
        }
        for (int i = 0; i < size - 1; i++) {
            ans+=Math.min(left_max[i],right_max[i])-height[i];
        }
        return ans;
    }
    /*
    暴力
     */
    public int trap1(int[] height) {
        int ans=0;
        // 一个个确定每一块剩多少水，这里第一块和最后一块是盛不了水的，不考虑
        for (int i = 1; i < height.length-1; i++) {
            int max_left=0,max_right=0;
            //Search the left part for max bar size
            for (int j = i; j >=0 ; j--) {
                max_left=Math.max(max_left,height[j]);
            }
            //Search the right part for max bar size
            for (int j = i; j <height.length ; j++) {
                max_right=Math.max(max_right,height[j]);
            }
            ans+=Math.min(max_left,max_right)-height[i];
        }
        return ans;
    }
}
