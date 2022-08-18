package hw.基础学习.基础特性;

import static hw.基础学习.基础特性.SwitchTest.Size;

public class SwitchTest {
    enum Size{
        MALL
    }
    public static void main(String[] args) {
        Size size= Size.MALL;
        switch (size){
            case MALL:
                break;
        }
        int sw = 9;
        // default four three
        switch (sw) {
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
                break;
            default:
                System.out.println("default"); // 如果找不到目标就直接到default,然后会继续往下走，
            case 9: // 如果有9直接到9，然后往下走
                System.out.println("four");
            case 3:
                System.out.println("three");
        }


        String s=null;
        switch (s){
            case "null":
                System.out.println("null");
            default:
                System.out.println("de");
        }
    }
}
