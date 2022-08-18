package hw.基础学习.jvm;

public final class FinalClass {

    public static void main(String[] args) {
        int y=2, x=4;
        while(--x != x/y){
            System.out.println(x/y); // 第4次没有进来
            System.out.println(0/2);
        }

        Long sum = 0L;
        for (long i = 0L; i < 2; i++)
        {
            sum += i;
            System.out.println(sum);
        }

    }
}
