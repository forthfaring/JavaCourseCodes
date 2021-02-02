package java0.conc0301.op;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitAndNotify01 {

    public static void main(String[] args) {
        Queue q = new Queue();
        Thread t1 = new Thread(q::consume);
        Thread t2 = new Thread(q::produce);
        t1.start();
        t2.start();
    }


    private static class Queue {
        private AtomicInteger count = new AtomicInteger(0);

        private static final int SIZE = 10;

        public void consume() {
            while (true) {
                synchronized (this) {
                    System.out.println("consume " + count.get());
                    if (count.get() <= 0) {
                        System.out.println("队列为空。。");
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    } else {
                        count.decrementAndGet();
                    }
                    notifyAll();
                }
            }
        }

        public void produce() {
            while (true) {
                synchronized (this) {
                    System.out.println("produce " + count.get());
                    if (count.get() >= SIZE) {
                        System.out.println("队列满了");
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    } else {
                        count.incrementAndGet();
                    }
                    notifyAll();
                }
            }
        }
    }
}
