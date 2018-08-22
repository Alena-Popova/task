package models;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Data
public class User extends Figure implements Runnable {

    public User(Cell cell, Board board, String name) {
        super(cell, board, name);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("thread bomberman");

        while (!Thread.currentThread().isInterrupted()) {
            try {
                final Cell dest = this.getBoard().randomMove(this.getCurrentCell());
                if (this.getBoard().move(this.getCurrentCell(), dest)) {
                    this.setCurrentCell(dest);
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
