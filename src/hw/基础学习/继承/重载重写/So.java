package hw.基础学习.继承.重载重写;

public class So extends Fa{

    public So(){
        System.out.println("so");
    }

    public Number sayNumber(){
        return 1;
    }

    public int sayInt(){
        Number i=1;
        return (int)i;
    }

    public int zai() {
        return 0;
    }

    public String zai(int i) {
        return "asd";
    }

    public int fafa(){
        return 0;
    }

    // 返回值类型无法作为重载区分标准
    /*public int zai(int i) {
        return 0;
    }*/

    int zai(String i,int j) {
        return 1;
    }

    public int zai(int j,String i) {
        return 1;
    }


    public static void main(String[] args) {
        So s=new So();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
