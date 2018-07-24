package synh;

public class Output {

    /**
     * synchronized тут надо постаивть
     * @param msg
     */
    public void out(String msg) {
        System.out.print("{" + msg.substring(0, msg.length()/2));
        try {
            Thread.sleep(300);
        }catch (InterruptedException io) {
            System.out.println(io);
        }
        System.out.println( msg.substring(msg.length()/2, msg.length()) + "}");
    }
}
