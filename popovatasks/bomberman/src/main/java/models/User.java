package models;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Data
public class User extends Figure implements Runnable {

    public User(Cell cell, Board board) {
        super(cell, board);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("thread bomberman");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                final Cell dest = this.getBoard().randomMove(this.getCurrentCell());
                if (this.getBoard().move(this.getCurrentCell(), dest)) {
                    System.out.println("gap to " + dest.toString());
                    this.setCurrentCell(dest);
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
