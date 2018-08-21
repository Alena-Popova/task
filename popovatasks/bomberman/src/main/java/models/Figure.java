package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.ReentrantLock;

@Data
@ThreadSafe
public class Figure {

    @GuardedBy("this")
    @Getter
    private Cell currentCell;

    private final Board board;

    public Figure(Cell cell, Board board) {
        this.currentCell = cell;
        this.board = board;
    }

    public void setCoordinate(Cell cell) {
        this.currentCell = cell;
    }

}
