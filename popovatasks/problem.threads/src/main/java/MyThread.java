public class MyThread implements Runnable {
    String name;
    Thread main;

    public MyThread(String s) {
        name = s;
        main = new Thread(this, name);
        System.out.println("New thread : " + this.name);
        main.start();
    }

    @Override
    public void run() {
        int i = 5;
        while (i > 0) {
            System.out.println(name + " " + i);
            i--;
            try {
                Thread.sleep(300);
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }
        }
    }
}
