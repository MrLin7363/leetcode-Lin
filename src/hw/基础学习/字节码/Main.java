package hw.基础学习.字节码;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        int a = 5;
        double b = 2.00;
        String c = "3";
        long l = 3;
        add(c, a, l, b);
    }

    public static void add(String a, int b, long l, double c) {
        double v = Integer.valueOf(a) + b + c;
        System.out.println(v);
    }
}
