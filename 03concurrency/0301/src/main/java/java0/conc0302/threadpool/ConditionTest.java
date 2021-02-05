package java0.conc0302.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc
 * @Author wfy
 * @Date 2021/2/4 10:38
 */
public class ConditionTest {
    static Lock lock = new ReentrantLock(true);
    static Condition fullCondition = lock.newCondition();
    static Condition emptyCondition = lock.newCondition();
    static int size = 0;
    static int capacity = 10;

    //空了才入队。 满了才出队。
    public static void main(String[] args) {
        enqueue();
        dequeue();
    }

    public static void enqueue() {
        new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                    if (size < capacity) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        ++size;
                        System.out.println("enqueue " + size);
                    } else {
                        emptyCondition.signalAll();
                        fullCondition.await();
                    }
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }).start();
    }

    public static void dequeue() {
        new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                    if (size > 0) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        --size;
                        System.out.println("dequeue " + size);
                    } else {
                        fullCondition.signalAll();
                        emptyCondition.await();
                    }
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }).start();
    }
}
