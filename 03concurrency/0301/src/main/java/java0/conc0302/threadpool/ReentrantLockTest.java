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
public class ReentrantLockTest {
    static Lock lock = new ReentrantLock(true);
    static int count = 0;

    public static void main(String[] args) {
        Thread interT = null;
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(ReentrantLockTest::incr);
            t.start();
            if (i == 1) {
                interT = t;
            }
        }
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void incr() {
        boolean locked = false;
        try {
            locked = lock.tryLock(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        if (locked) {
            Condition c = lock.newCondition();
            try {
                for (int i = 0; i < 100000; i++) {
                    count++;
                    System.out.println(count);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}