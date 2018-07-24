package wait;

public class Qeue {
    private int count = 0;
    /**
     * тут очень важно стаивть вот этот флаг, без него не будет работать
     */
    private boolean flag = false;

    public synchronized void setCount() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
        this.count++;
        System.out.println("add " + this.count);
        this.flag = true;
        notify();
    }

    public synchronized int getCount() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
        System.out.println("out " + this.count);
        this.flag = false;
        notify();
        return count;
    }
}
