package hw.基础学习.jvm;

import java.util.ArrayList;
import java.util.List;

public  abstract  class AbstractTest {
    abstract void test();

    void tt() {

    }

    public AbstractTest(){
        List<String> list=new ArrayList<>();
        String[]array=list.toArray(new String[0]);
    }
}
