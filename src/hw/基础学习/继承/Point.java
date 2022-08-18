package hw.基础学习.继承;

public class Point {

    private int x;

    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        modifyPoint(p1, p2);
        System.out.println("[" + p1.x + "," + p1.y + "],[" + p2.x + "," + p2.y + "]");
    }

    private static void modifyPoint(Point p1, Point p2) {
        Point tmpPoint = p1;
        p1 = p2;
        p2 = tmpPoint;
        p1.setLocation(5, 5);
        p2 = new Point(5, 5);
    }
    /*
    答案解析：开始在内存中创建p1 p2两个对象，假设p1地址是xxx@493,p2地址是xxx@494.再进入modifyPoint后
Point tmpPoint = p1; // tmpPoint地址xxx@493
p1 = p2;// p1地址xxx@494
p2 = tmpPoint;//p2地址xxx@493
p1.setLocation(5,5); // xxx@494 494的x和y的值都是5.
p2 = new Point(5,5); // 新创建对象，p2地址xxx@495
跳出modifyPoint后，局部变量xxx@495被回收。p1地址是xxx@493,p2地址是xxx@494。p1.x p1.y为0，p2.x,p2.y为5.
     */

}
