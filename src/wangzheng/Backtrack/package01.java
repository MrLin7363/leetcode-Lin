package wangzheng.Backtrack;

import java.util.regex.Pattern;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/06 16:42
 * @Describe: 回溯法
 */
public class package01 {

    public static int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值
    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
// w背包重量；items表示每个物品的重量；n表示物品个数
// 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
// f(0, 0, a, 10, 100)
    public static void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品，条件成立就说明是一种装法
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i+1, cw, items, n, w);//当前物品不装进背包
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i+1,cw + items[i], items, n, w);//当前物品装进背包
        }
    }

    public static void main(String[] args) {
        int[] a=new int[]{2,3,4,5,6,7,8,9,10,11};
        f(0, 0, a, 10, 100);

    }
}
