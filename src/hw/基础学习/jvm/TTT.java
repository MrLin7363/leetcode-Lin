package hw.基础学习.jvm;

import java.io.IOException;

public abstract  interface TTT extends YYY{

    public final static int s=0;

    public abstract void ww();

    public default void vv(){

    }

    public static void v2v(){

    }

    final static int sj=1;

    public static void main(String[] args) {
        TTT t= new TTT() {
            @Override
            public void ww() {
                vv();
            }
        };
        t.vv();
        try {
            Process proc = Runtime.getRuntime().exec(new String[] { "ping", "userInput"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
