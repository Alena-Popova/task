package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * шаблон для любых фигур на доске.
 * Должны ему присвоить ячейку начал пути, доску  и наименование фигуры
 */
@Data
@ThreadSafe
public class Figure {

    @GuardedBy("this")
    @Getter
    private Cell currentCell;

    private final Board board;

    private final String name;

    public Figure(Cell cell, Board board, String name) {
        this.currentCell = cell;
        //устаначливаем фигуру
        this.currentCell.setFigure(this);
        //блокируем клетку
        this.currentCell.lock();
        this.board = board;
        this.name = name;
    }

    public void setCoordinate(Cell cell) {
        this.currentCell = cell;
    }

}
