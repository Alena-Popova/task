import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification extends ExecutorServicePool {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    String subject;
    String body;

    @Override
    void emailTo(User user) {
        subject = String.format("Notification %s to email %s", user.getName(), user.getEmail());
        body = String.format("Add a new event to %s", user.getName());
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject, body, user.getEmail());
            }
        });
    }


    @Override
    void send(String suject, String body, String email) {

    }

    @Override
    void stop() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }
}
