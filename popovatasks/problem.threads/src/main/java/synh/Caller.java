package synh;

public class Caller implements Runnable {

    String title;
    Output output;
    Thread main;

    public Caller(Output _outSrc, String _src) {
        title = _src;
        output = _outSrc;
        main = new Thread(this, title);
        main.start();
    }

    @Override
    public void run() {
       output.out(title);
    }
}

