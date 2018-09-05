package logic;

import persistent.Store;

public class ValidateService implements Store {

    private static ValidateService instanse;

    public ValidateService() {

    }

    public static ValidateService getInstance() {
        if (instanse == null) {
            instanse = new ValidateService();
        }
        return instanse;
    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void findAll() {

    }

    @Override
    public void findById() {

    }
}
