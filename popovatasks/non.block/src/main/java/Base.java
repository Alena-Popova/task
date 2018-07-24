import java.util.Objects;

public class Base {

    private int id;
    private String title;
    private volatile int version;

    public Base(int _id, String _title) {
        this.title = _title;
        this.id = _id;
    }

    public void update() {
        this.version++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                Objects.equals(title, base.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", version=" + version +
                '}';
    }
}
