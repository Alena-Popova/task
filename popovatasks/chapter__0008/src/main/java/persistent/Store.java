package persistent;

public interface Store {

    void add();

    void update();

    void delete();

    void findAll();

    void findById();
}
