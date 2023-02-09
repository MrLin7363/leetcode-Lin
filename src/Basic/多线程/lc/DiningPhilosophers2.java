package Basic.多线程.lc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 让 1个哲学家可以 “同时”拿起2个叉子(搞个临界区)
 */
public class DiningPhilosophers2 {
    //1个Fork视为1个ReentrantLock，5个叉子即5个ReentrantLock，将其都放入数组中
    private final ReentrantLock[] lockList = {new ReentrantLock(),
        new ReentrantLock(),
        new ReentrantLock(),
        new ReentrantLock(),
        new ReentrantLock()};

    //让 1个哲学家可以 “同时”拿起2个叉子(搞个临界区)
    private ReentrantLock pickBothForks = new ReentrantLock();

    public DiningPhilosophers2() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
        Runnable pickLeftFork,
        Runnable pickRightFork,
        Runnable eat,
        Runnable putLeftFork,
        Runnable putRightFork) throws InterruptedException {

        int leftFork = (philosopher + 1) % 5;    //左边的叉子 的编号
        int rightFork = philosopher;    //右边的叉子 的编号

        pickBothForks.lock();    //进入临界区

        lockList[leftFork].lock();    //拿起左边的叉子
        lockList[rightFork].lock();    //拿起右边的叉子

        pickLeftFork.run();    //拿起左边的叉子 的具体执行
        pickRightFork.run();    //拿起右边的叉子 的具体执行

        pickBothForks.unlock();    //退出临界区

        eat.run();    //吃意大利面 的具体执行

        putLeftFork.run();    //放下左边的叉子 的具体执行
        putRightFork.run();    //放下右边的叉子 的具体执行

        lockList[leftFork].unlock();    //放下左边的叉子
        lockList[rightFork].unlock();    //放下右边的叉子
    }
}
