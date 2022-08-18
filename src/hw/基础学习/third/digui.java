package hw.基础学习.third;

public class digui {

    private static int count=0;

    private static int f(int n){
        count++;
        if (n<=2){
            return 1;
        }
        boolean ss=false;
        return f(n-2)+f(n-4)+1;
    }

    public static void main(String[] args) {
        System.out.println(f(10));
    }
}
