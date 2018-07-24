import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NonBlockCacheTest {

    ExecutorService service = Executors.newFixedThreadPool(1);

    Cache nonBlockCache = new NonBlockCache();

    @Test
    public void testAdd() {
        int i = 0;
        while (i < 10) {
            try {
                addF(i, String.format("%d tile", i));
                i++;
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
        assertThat(new Base(1, "1 tile"), is(nonBlockCache.get(1)));
        assertThat(new Base(2, "2 tile"), is(nonBlockCache.get(2)));
        assertThat(new Base(3, "3 tile"), is(nonBlockCache.get(3)));
        assertThat(null, is(nonBlockCache.get(60)));
    }


    @Test
    public void testUpdate() {
        int i = 0;
        while (i < 10) {
            try {
                addF(i, String.format("%d tile", i));
                updateF(i, String.format("%d tile", i + 1));
                i++;
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
        assertThat(new Base(1, "2 tile"), is(nonBlockCache.get(1)));
        assertThat(new Base(2, "3 tile"), is(nonBlockCache.get(2)));
        assertThat(new Base(3, "4 tile"), is(nonBlockCache.get(3)));
        assertThat(null, is(nonBlockCache.get(60)));
    }

    @Test(expected = OptimisticException.class)
    public void testConcurentUpdate() {
        int i = 0;
        while (i < 2) {
            try {
                addF(i, String.format("%d tile", i));
                doAll(i, String.format("service %d tile", i + 1));
                i++;
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
        service.shutdown();
    }

    public void addF(int i, String s) throws InterruptedException {
        Thread add = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlockCache.add(new Base(i, s));
            }
        });
        add.start();
        add.join();
    }

    public void updateF(int i, String s) throws InterruptedException, OptimisticException {
        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlockCache.update(new Base(i, s));
            }
        });
        update.start();
        update.join();
    }

    public void doAll(int i, String s) throws InterruptedException {
        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlockCache.update(new Base(i, s));
            }
        });
        update.start();
        service.submit(new Runnable() {
            public void run() {
                nonBlockCache.update(new Base(i, s));
            }
        });
    }

}