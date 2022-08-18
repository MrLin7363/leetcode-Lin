package hw.基础学习.异常;

public class TestClass {
    public TestClass(){
        throw new IllegalArgumentException();
    }
    public void print(){
        System.out.println("asd");
    }

    public static void main(String[] args) {
        try {
            TestClass testClass = (TestClass)Class.forName("hw.基础学习.异常.TestClass").newInstance();
            testClass.print();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
