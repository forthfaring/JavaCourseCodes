package java0.conc0302.threadpool;

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
        for (int i = 0; i < 10; i++) {
            new Thread(ReentrantLockTest::incr).start();
        }

    }

    public static void incr() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                count++;
                System.out.println(count);
            }
        } finally {
            lock.unlock();
        }

    }
}