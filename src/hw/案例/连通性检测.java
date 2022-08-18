 

package hw.案例;

/**
 * desc: 两行的二维矩阵
 * 只需要判断该节点对面的三个节点 是否构成阻塞， 阻断点可以理解为上下两个点之间的连线，
 * 如果上电，对面三个都已经下电，就是减去三个阻断连线
 *
 * 第二种方法DFS 递归线路 上右下  不要先上下，否则太慢了
 *
 * @author junlin
 * @since 2022/1/5
 **/
public class 连通性检测 {

    /*
    阻断线法
      1
   1  1  1
   像上面这个就是有三条阻断线
     */
    public int[] checkConnectivity(int num, int[][] operations) {
        int breakLine = 0;
        int[][] state = new int[2][num];
        int[] result = new int[num];
        for (int i = 0; i < operations.length; i++) {
            int type = operations[i][0]; // 主还是副设备
            int index = operations[i][1]; // 下标
            int ops = (type == 0) ? 1 : 0; // 对偶设备
            int add = state[type][index] == 0 ? 1 : -1; // 如果当前设备是通的，只能是增加阻断;反之

            // 当前设备对面的设备是不通的，增加或减少阻断线
            if (state[ops][index] == 1) {
                breakLine += add;
            }
            // 斜上角的两个设备
            if (index < num - 1 && state[ops][index + 1] == 1) {
                breakLine += add;
            }
            if (index > 1 && state[ops][index - 1] == 1) {
                breakLine += add;
            }

            // 更改状态表
            state[type][index] = state[type][index] == 0 ? 1 : 0;
            // 记录结果
            result[i] = breakLine > 0 ? 0 : 1;
        }
        return result;
    }

}
