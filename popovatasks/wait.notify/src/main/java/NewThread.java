public class NewThread implements Runnable {
    String title;
    Thread main;
    boolean flag;

    public NewThread(String name) {

        title = name;
        main = new Thread(this, this.title);
        flag = false;
        main.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++) {
                System.out.println(i + "  - " + Thread.currentThread().getName());
                Thread.sleep(200);
                synchronized (this) {
                    while (flag)
                        wait();
                }
            }
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

    }

    public synchronized void suspend() {
        flag = true;
    }

    public synchronized void resume() {
        flag = false;
        notify();
    }
}
