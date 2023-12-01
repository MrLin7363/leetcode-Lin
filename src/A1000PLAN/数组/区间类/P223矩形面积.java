package A1000PLAN.数组.区间类;

/**
 *desc:
 *@author lin
 *@since 2023/11/29
 **/
public class P223矩形面积 {
    /*
    容斥原理
    1.确定 y垂直相交 ->相当于y轴 两段距离的相交长度
    2.确定 x水平边界相交
    3.两个矩形面积- (水平相交*垂直相交=覆盖面积)
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 区间相交 ：求一条直线上两段距离的长度  左端点最大，右端点最小
        // 考虑到不相交，所以和0比取最大
        int x = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int y = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
        int total = (ay2 - ay1) * (ax2 - ax1) + (by2 - by1) * (bx2 - bx1);
        return total - x * y;
    }
}
