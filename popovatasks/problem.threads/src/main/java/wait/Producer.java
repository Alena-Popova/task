package wait;

public class Producer implements Runnable {
    Qeue qeue;
    Thread thread;

    public Producer(Qeue _qeue) {
        qeue = _qeue;
        thread = new Thread(this, "PC");
        thread.start();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            qeue.setCount();
            i++;
        }
    }
}
