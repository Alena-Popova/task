package models;

import lombok.Data;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Board {

    /**
     * граница по X
     */
    private final int borderX;
    /**
     * граница по Y
     */
    private final int borderY;
    /**
     * само поле
     */
    private final Cell[][] cells;

    public Board(int borderX, int borderY) {
        this.borderX = borderX;
        this.borderY = borderY;
        cells = new Cell[borderX][borderY];
        init();
    }

    /**
     * Инициализируем массив
     */
    public void init() {
        for (int i = 0; i < borderX; i++) {
            for (int j = 0; j < borderY; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }


    /**
     * поверяем точку : входит ли она в границы доски
     *
     * @param x
     * @param y
     * @return true если входит
     */
    public boolean chechBorders(int x, int y) {
        if (x >= borderX || y >= borderY || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    /**
     * движение по доске с source в dest
     *
     * @param source
     * @param dest
     * @return
     * @throws InterruptedException
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
        if (cells[dest.getX()][dest.getY()].tryLock(500L, TimeUnit.MILLISECONDS)) {
            if (cells[source.getX()][source.getY()].isHeldByCurrentThread()) {
                cells[source.getX()][source.getY()].unlock();
                cells[dest.getX()][dest.getY()].lock();
            }
            result = true;
        }
        return result;
    }

    /**
     * выбирается рандомное направление движение диапазоном в одну клетку
     *
     * @param source
     * @return
     */
    public Cell randomMove(Cell source) throws InterruptedException {
        int random[] = {-1, 0, 1};
        int x = random[new Random().nextInt(3)];
        int y = random[new Random().nextInt(3)];

        while (!chechBorders(source.getX() + x, source.getY() + y)
                || !cells[source.getX() + x][source.getY() + y].tryLock(500L, TimeUnit.MILLISECONDS)) {
            x = random[new Random().nextInt(3)];
            y = random[new Random().nextInt(3)];
        }

        return cells[source.getX() + x][source.getY() + y];

    }

    public Cell getStartRandomPosition() {
        int x = (int)(Math.random() * this.borderX);
        int y = (int)(Math.random() * this.borderY);
        return cells[x][y];
    }

    @Override
    public String toString() {
        return Board.class.getName();
    }
}
