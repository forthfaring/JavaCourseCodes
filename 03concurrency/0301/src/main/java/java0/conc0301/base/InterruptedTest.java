package java0.conc0301.base;

import java.util.concurrent.TimeUnit;

/**
 * @Desc
 * @Author wfy
 * @Date 2021/1/26 13:32
 */
public class InterruptedTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("run:" + Thread.currentThread().getName() + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            Thread.interrupted();
            System.out.println("alive!");
        });
        t1.start();
        t1.interrupt();
        System.out.println("111");
    }
}
