package task.third;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class User implements Comparable<User>{

    private int id;


    private int amount;

    public User( int srcId) {
        this.id = srcId;
    }

    public User( int srcId, int srcAmount) {
        this.id = srcId;
        this.amount = srcAmount;
    }

    public int compareTo(User o) {
        return this.id - o.id;
    }

    public int getId() {
        return id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void changeAmount(int delta) {
        this.amount = this.amount + delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
