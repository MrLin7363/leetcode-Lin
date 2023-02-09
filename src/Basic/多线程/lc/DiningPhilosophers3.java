package Basic.多线程.lc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 只允许1个哲学家就餐”是严格的“串行”
 */
public class DiningPhilosophers3 {
    //只允许1个哲学家就餐
    private ReentrantLock pickBothForks = new ReentrantLock();

    public DiningPhilosophers3() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
        Runnable pickLeftFork,
        Runnable pickRightFork,
        Runnable eat,
        Runnable putLeftFork,
        Runnable putRightFork) throws InterruptedException {

        pickBothForks.lock();    //进入临界区

        pickLeftFork.run();    //拿起左边的叉子 的具体执行
        pickRightFork.run();    //拿起右边的叉子 的具体执行

        eat.run();    //吃意大利面 的具体执行

        putLeftFork.run();    //放下左边的叉子 的具体执行
        putRightFork.run();    //放下右边的叉子 的具体执行

        pickBothForks.unlock();    //退出临界区
    }
}
