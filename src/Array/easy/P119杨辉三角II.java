package Array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/04 19:07
 * @Describe: 与上一题区别只是不用记住每一层的节点
 */
public class P119杨辉三角II {

    /*
    把 pre 数组省去，直接用cur
     */
    public List<Integer> getRow(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }

    /*
    逆序，不存在覆盖情况
     */
    public List<Integer> getRow4(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);//补上每层的最后一个 1
        }
        return cur;
    }

    /*
    滚动数组：
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> pre =new ArrayList<>();
        List<Integer> cur =new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j==0||j==i){ // 第一或者最后
                    cur.add(1);
                }else {
                    cur.add(pre.get(j)+pre.get(j-1));
                }
            }
                pre=cur;
        }
        return cur;
    }

    public List<Integer> getRow2(int numRows) {
        List<Integer> pre =new ArrayList<>();
        List<Integer> cur =new ArrayList<>();
        cur.add(1);
        if (numRows<1){
            return cur;
        }
        // 两次遍历
        for (int i = 2; i <= numRows+1; i++) {
            cur=new ArrayList<>();
            cur.add(1);// 开头元素
            // 取上一行
            for (int j = 1; j < i-1; j++) {
                Integer value=pre.get(j)+pre.get(j-1);
                cur.add(value);
            }
            cur.add(1); // 结尾元素
            pre=cur;
        }
        return cur;
    }

}
