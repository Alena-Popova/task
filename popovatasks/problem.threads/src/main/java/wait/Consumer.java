package wait;

public class Consumer implements Runnable {
    Qeue qeue;
    Thread thread;

    public Consumer(Qeue _qeue) {
        qeue = _qeue;
        thread = new Thread(this, "Consumer");
        thread.start();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            qeue.getCount();
            i++;
        }
    }
}
