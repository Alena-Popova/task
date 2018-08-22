package models;

import lombok.Data;

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
     * игрок
     */
    private final User user;
    /**
     * само поле
     */
    private final Cell[][] cells;

    public Board(int borderX, int borderY) {
        this.borderX = borderX;
        this.borderY = borderY;
        cells = new Cell[borderX][borderY];
        init();
        user = new User(new Cell(0,0), this, "Bomberman");
    }

    /**
     * Инициализируем массив
     */
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


    /**
     * поверяем точку : входит ли она в границы доски
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
     * @param source
     * @param dest
     * @return
     * @throws InterruptedException
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException{
        boolean result = false;
        if (dest.tryLock(500L, TimeUnit.MILLISECONDS) && !source.isEmpty()) {
            if (source.isHeldByCurrentThread()) {
                source.unlock();
                System.out.println("unlock");
            }
            source.ridCell().setCoordinate(dest);
            dest.setFigure(user);
            System.out.println(String.format("move to %s", dest.toString()));
            result = true;
        }
        return result;
    }

    /**
     * выбирается рандомное направление движение диапазоном в одну клетку
     * @param source
     * @return
     */
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
