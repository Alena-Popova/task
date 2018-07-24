package task.second;

public class CheckThread {
    int count = 0;

    public class ThreadF extends Thread {

        @Override
        public void run() {
            addCount(1);
        }
    }

    public void addCount(int value) {
        this.count += value;
    }

    public int getCount() throws InterruptedException {
        ThreadF threadF = new ThreadF();
        ThreadF threadS = new ThreadF();

        threadF.start();
        threadS.start();
        threadF.join();
        //threadS.join();
        return this.count;
    }

    public static void main(String[] args) {
        CheckThread checkThread = new CheckThread();
        try {
            System.out.println(checkThread.getCount());
        } catch (InterruptedException ie) {
            System.out.printf(ie.getMessage());
        }
    }
}
