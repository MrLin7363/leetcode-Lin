package Other.DesignPattern;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/09 13:13
 * @Describe: 单例模式
 */
public class Singleton {
    //懒汉不安全 lazy  懒汉就是调用的时候才实例化
   /* public static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if (instance==null) instance=new Singleton();
        return instance;
    }*/
//    懒汉安全 lazy，上面的加个同步即可
//    public static synchronized Singleton getInstance(){

    //饿汉式 不lazy,线程安全   类加载时就初始化
    /*public static Singleton instance=new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }*/

    //双检锁/双重校验锁（DCL，即 double-checked locking）,lazy，线程安全
    private volatile static Singleton singleton;
    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null)
                    singleton=new Singleton();
            }
        }
        return singleton;
    }
}
