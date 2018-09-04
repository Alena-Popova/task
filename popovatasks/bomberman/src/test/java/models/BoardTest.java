package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void init() throws InterruptedException {
        Board board = new Board(10,10);
        User user = new User(board, "Bomberman");
        Thread game = new Thread(user);
        game.start();
        try {
            Thread.sleep(5000);
            game.interrupt();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        board.move(new Cell(0,0), new Cell(1,1));
    }
}