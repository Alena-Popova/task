package models;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Data
public class User extends Figure implements Runnable {

    public User(Board board, String name) {
        super(board, name);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("thread bomberman");

        this.init();

        while (!Thread.currentThread().isInterrupted() && this.getCurrentCell() != null) {
            try {
                final Cell dest = this.getBoard().randomMove(this.getCurrentCell());
                if (this.getBoard().move(this.getCurrentCell(), dest)) {
                    this.setCurrentCell(dest);
                    LoggerProject.printWhatDone(this.getName(), "move to  " + dest.toString());
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        try {
            Cell start = this.getBoard().getStartRandomPosition();
            while (!this.getBoard().getCells()[start.getX()][start.getY()].tryLock(500L, TimeUnit.MILLISECONDS)) {
                start = this.getBoard().getStartRandomPosition();
            }
            this.setCurrentCell(start);
            LoggerProject.printWhatDone(this.getName(), "set Start point " + start.toString());
        } catch (InterruptedException e) {
            LoggerProject.setLogs(User.class.getName(), "no set start position");
        }
    }


}
