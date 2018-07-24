public class MultyThread {
        public static void main(String[] args) {
            new MyThread("One");
            new MyThread("Two");
            new MyThread("Thri");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException io) {
                System.out.println(io.getMessage());
            }

            System.out.println("end main");

        }
}
