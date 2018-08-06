
public abstract class ExecutorServicePool {
    abstract void emailTo(User user);

    abstract void send(String suject, String body, String email);

    abstract void stop();
}

