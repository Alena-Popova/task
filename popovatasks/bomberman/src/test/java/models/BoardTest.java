package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void init() throws InterruptedException {
        Board board = new Board(10,10);
        try {
            board.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}