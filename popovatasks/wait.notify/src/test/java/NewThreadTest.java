import org.junit.Test;

import static org.junit.Assert.*;

public class NewThreadTest {

    @Test
    public void testChangeActive() {
        NewThread newThread1 = new NewThread("Первый");
        NewThread newThread2 = new NewThread("Второй");

        try {
            Thread.sleep(1000);
            newThread1.suspend();
            System.out.println("остановка 1");
            Thread.sleep(1000);
            newThread1.resume();
            System.out.println("возобноыление 1");
            Thread.sleep(1000);
            newThread2.suspend();
            System.out.println("остановка 2");
            Thread.sleep(1000);
            newThread2.resume();
            System.out.println("возобновление 2");
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

        try {
            System.out.println("ожидание завершения потоков");
            newThread1.main.join();
            newThread2.main.join();
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }

}