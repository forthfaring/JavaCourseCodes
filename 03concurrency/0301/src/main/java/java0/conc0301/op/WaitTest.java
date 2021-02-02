package java0.conc0301.op;

import java.util.concurrent.TimeUnit;

/**
 * @Desc
 * @Author wfy
 * @Date 2021/1/25 15:12
 */
public class WaitTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (WaitTest.class) {
                    System.out.println(1);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        WaitTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    System.out.println(3);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        while (true) {
            synchronized (WaitTest.class) {
                System.out.println(2);
                WaitTest.class.notify();
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
