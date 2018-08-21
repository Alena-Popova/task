package models;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Board {

    private int borderX;
    private int borderY;
    private User user;
    private final Cell[][] cells;

    public Board(int borderX, int borderY) {
        this.borderX = borderX;
        this.borderY = borderY;
        cells = new Cell[borderX][borderY];
        init();
        user = new User(new Cell(0,0), this);
    }

    public void init () {
        for (int i = 0; i < borderX; i++) {
            for (int j = 0; j < borderY; j++) {
                this.cells[i][j] = new Cell(i,j);
            }
        }
    }

    /**
     * Start game.
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        Thread game = new Thread(user);	//Создание потока "myThready"
        game.start();
        System.out.println("start");
        Thread.sleep(10000);
    }



    public boolean chechBorders(int x, int y) {
        if (x >= borderX || y >= borderY || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    public boolean move(Cell source, Cell dest) throws InterruptedException{
        boolean result = false;
        if (dest.tryLock(500L, TimeUnit.MILLISECONDS) && !source.isEmpty()) {
            source.ridCell().setCoordinate(dest);
            source.unlock();
            result = true;
        }
        return result;
    }

    public Cell  randomMove(Cell source) {
        int random[] = {-1, 0, 1};
        int x = random[ new Random().nextInt(3)];
        int y = random[ new Random().nextInt(3)];

        while (!chechBorders(source.getX() + x, source.getY() + y)
                || !cells[source.getX() + x][source.getY() + y].isEmpty()) {
            x = random[ new Random().nextInt(3)];
            y = random[ new Random().nextInt(3)];
        }

        return cells[source.getX() + x][source.getY() + y];

    }

}
