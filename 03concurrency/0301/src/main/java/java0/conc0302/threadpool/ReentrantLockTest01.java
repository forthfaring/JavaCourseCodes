package java0.conc0302.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc
 * @Author wfy
 * @Date 2021/2/3 18:05
 */
public class ReentrantLockTest01 {
    static ReentrantLock lock = new ReentrantLock(true);
    static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(ReentrantLockTest01::incr);
            t.start();
        }
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void incr() {
        System.out.println(lock.getHoldCount());
        lock.lock();
        lock.lock();
        lock.lock();
        try {
            System.out.println(lock.getHoldCount());
        } finally {
            lock.unlock();
            lock.unlock();
            lock.unlock();
        }
        System.out.println(lock.getHoldCount());
    }
}