import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification extends ExecutorServicePool {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getName(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getName());
        pool.submit(()-> send(subject, body, user.getEmail()));
    }


    @Override
    void send(String subject, String body, String email) {

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
