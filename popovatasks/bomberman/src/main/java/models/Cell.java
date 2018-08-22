package models;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

@Data
@ToString
public class Cell extends ReentrantLock{

    private final int x;
    private final int y;

    private Figure figure;

    public Cell(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }


    /**
     * занимаем ячейку
     * @param figure
     * @return
     */
    public boolean takeCell(Figure figure) {
        boolean result = false;
        if (isEmpty()) {
            this.figure = figure;
            this.figure.setCoordinate(this);
            result = true;
        }
        return result;
    }

    /**
     * пустая ли ячйка
     * */
    public boolean isEmpty() {
        return figure == null;
    }

    /**
    * очищаем ячейку
    * */
    public Figure ridCell() {
        Figure resultRid = figure;
        if (!isEmpty()) {
            figure = null;
        }
        return resultRid;
    }


}
