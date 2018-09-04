package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
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

    public Figure(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }


    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(board, figure.board) &&
                Objects.equals(name, figure.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, name);
    }

    @Override
    public String toString() {
        return name + " {"
                + currentCell.toString() +
                ", on board=" + board +
                '}';
    }
}
