package models;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

@Data
@ToString
public class Cell extends ReentrantLock {

    private final int x;
    private final int y;


    public Cell(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{x= " + x +
                ", y=" + y + '}';
    }


}
