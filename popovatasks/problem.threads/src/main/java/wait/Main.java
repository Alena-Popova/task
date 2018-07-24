package wait;

public class Main {

    public static void main(String[] args) {
        Qeue qeue = new Qeue();
        Producer producer = new Producer(qeue);
        Consumer consumer = new Consumer(qeue);


    }
}
