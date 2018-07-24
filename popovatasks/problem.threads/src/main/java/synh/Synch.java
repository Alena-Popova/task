package synh;

public class Synch {

    public static void main(String[] args) {
        Output output = new Output();
        Caller caller1 = new Caller(output, "Добро пожаловать");
        Caller caller2 = new Caller(output, "в синхронизированный");
        Caller caller3 = new Caller(output, "мир");
        try {
            caller1.main.join();
            caller1.main.join();
            caller1.main.join();
        }catch (InterruptedException io) {
            System.out.println(io);
        }
    }
}
